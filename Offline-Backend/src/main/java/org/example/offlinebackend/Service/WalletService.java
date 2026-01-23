package org.example.offlinebackend.Service;

import org.example.offlinebackend.Model.Chat;
import org.example.offlinebackend.Model.ChatResponse;
import org.example.offlinebackend.Model.UserSession;
import org.example.offlinebackend.Model.Wallet;
import org.example.offlinebackend.Repo.UserSessionRepo;
import org.example.offlinebackend.Repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {


    @Autowired
    UserSessionRepo userSessionRepo;

    @Autowired
    WalletRepo walletRepo;

    @Autowired
    RegisterService registerService;

    @Autowired
    WalletService walletService;

    @Autowired
    TopupService topupService;

    @Autowired
    ResetPin resetPin;

    @Autowired
    CheckBalanceService checkBalanceService;
    public ChatResponse Transaction(UserSession session, Chat chat) {
        ChatResponse chatresponse=new ChatResponse();

        Wallet wallet

        if(session.getCurrent_status()==null){
            session.setCurrent_status("SET_AMOUNT");
            userSessionRepo.save(session);
            chatresponse.setReply("Enter Amount");
            return chatresponse;
        }

        if(session.getCurrent_status().equals("SET_AMOUNT")){

            Wallet wallet=walletRepo.findByphonenumber(chat.getPhone());
            int walletBalance=wallet.getBalance();
            int Amount=Integer.parseInt(chat.getMessage());
            if(walletBalance<Amount){
                chatresponse.setReply("Insufficient Balance");
                return  chatresponse;
            }

            session.setCurrent_status("SET_MOBILE");
            userSessionRepo.save(session);
            chatresponse.setReply("Enter merchant mobile number");
            return chatresponse;

        }

        if(session.getCurrent_status().equals("SET_MOBILE")){

            session.setCurrent_status("SET_PIN");
            userSessionRepo.save(session);
            chatresponse.setReply("Enter Pin");
            return chatresponse;

        }

        if(session.getCurrent_status().equals("SET_PIN")){
            session.setCurrent_status("PENDING");
            userSessionRepo.save(session);
        }

        return chatresponse;

    }
}
