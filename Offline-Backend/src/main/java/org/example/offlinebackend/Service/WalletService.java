package org.example.offlinebackend.Service;

import org.example.offlinebackend.Model.*;
import org.example.offlinebackend.Repo.UserSessionRepo;
import org.example.offlinebackend.Repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private UserSessionRepo userSessionRepo;

    @Autowired
    private TokenService tokenService;

    public ChatResponse transaction(UserSession session, Chat chat) {

        ChatResponse response = new ChatResponse();
        if(session.getPin_attempts()==null){
            session.setPin_attempts(0);
        }

        Wallet sender=walletRepo.findByphonenumber(chat.getPhone());
        if(sender==null){
            response.setReply("first you register");
            return response;
        }

        if (session.getCurrent_status() == null) {
            session.setCurrent_status("SET_AMOUNT");
            userSessionRepo.save(session);
            response.setReply("Enter Amount");
            return response;
        }

        if ("SET_AMOUNT".equals(session.getCurrent_status())) {

            int amount;
            try {
                amount = Integer.parseInt(chat.getMessage());
            } catch (Exception e) {
                response.setReply("Invalid Amount");
                return response;
            }

            if (sender.getBalance() < amount) {
                response.setReply("Insufficient Balance"+"\n"+"your current balance:"+sender.getBalance() );
                return response;
            }

            session.setAmount(amount);
            session.setCurrent_status("SET_MOBILE");
            userSessionRepo.save(session);
            response.setReply("Enter receiver mobile number");
            return response;
        }
        //mobile number
        if ("SET_MOBILE".equals(session.getCurrent_status())) {

            Wallet receiver = walletRepo.findByphonenumber(chat.getMessage());
            if (receiver == null) {
                response.setReply("Receiver not registered in wallet");
                return response;
            }

            session.setReceiver_mobile(chat.getMessage());
            session.setCurrent_status("SET_PIN");
            userSessionRepo.save(session);

            response.setReply("Enter PIN");
            return response;
        }

        if ("SET_PIN".equals(session.getCurrent_status())) {

            if (!sender.getPin().equals(chat.getMessage())) {

                if (session.getPin_attempts() == 2) {
                    response.setReply("You are blocked for 24 hours");
                    userSessionRepo.delete(session);
                    return response;
                }

                response.setReply("Wrong PIN. 3 wrong attempts will block you");
                session.setPin_attempts(session.getPin_attempts() + 1);
                userSessionRepo.save(session);
                return response;
            }

            int amount = session.getAmount();
            Wallet receiver =
                    walletRepo.findByphonenumber(session.getReceiver_mobile());

            sender.setBalance(sender.getBalance() - amount);
            PaymentToken token = tokenService.generateToken(
                    sender,
                    receiver.getPhonenumber(),
                    amount
            );
            userSessionRepo.delete(session);
            response.setReply(
                    "Payment Token: " + token.getTokenId() +
                            "\nAmount: " + amount +
                            "\nStatus: PENDING"
            );
            return response;
        }
        return response;
    }
}
