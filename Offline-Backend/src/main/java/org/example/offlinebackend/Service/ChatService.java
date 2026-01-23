package org.example.offlinebackend.Service;

import org.example.offlinebackend.Model.*;
import org.example.offlinebackend.Repo.ACCInformationRepo;
import org.example.offlinebackend.Repo.UserSessionRepo;
import org.example.offlinebackend.Repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ChatService {
    @Autowired
    UserSessionRepo userSessionRepo;
    @Autowired
    ACCInformationRepo accInformationRepo;
    @Autowired
    WalletRepo walletRepo;
    String MAIN_MENU;
    public ChatResponse ChatHandel(@RequestBody Chat chat) {
        ChatResponse chatResponse=new ChatResponse();
        UserSession userSession = userSessionRepo.findById(chat.getPhone()).orElse(null);
        if (userSession == null) {
            MAIN_MENU=
                    "Welcome to Offline Wallet Service\n" +
                            "--------------------------------\n" +
                            "1. Register\n" +
                            "2. Money Transfer\n" +
                            "3. Top Up\n" +
                            "4. Reset PIN\n" +
                            "5. Check Balance\n" +
                            "Reply with option number:";
            chatResponse.setReply(MAIN_MENU);
            userSession = new UserSession();
            userSession.setPhone_number(chat.getPhone());
            userSessionRepo.save(userSession);
            return chatResponse;
        }

        if(userSession.getUser_status()==null){
            userSession.setUser_status(chat.getMessage());
            userSessionRepo.save(userSession);
        }


        Wallet wallet1=walletRepo.findByphonenumber(chat.getPhone());
        if (userSession.getUser_status().equals("1") && wallet1==null) {

            /* STEP 1: Ask for Debit Card */
            if (userSession.getCurrent_status() == null) {
                userSession.setCurrent_status("WAITING_DEBITCARD");
                userSessionRepo.save(userSession);
                chatResponse.setReply("Enter Debit Card Number:");
                return chatResponse;
            }

            /* STEP 2: Receive Debit Card */
            if (userSession.getCurrent_status().equals("WAITING_DEBITCARD")) {
                AccInformation accInformation = accInformationRepo.
                        findById(userSession.getPhone_number())
                                .orElse(null);
                if(!accInformation.getDebitCard_number().equals(chat.getMessage())){
                    chatResponse.setReply("your debitcard number is wrong or you have not kyc you type any for restart the process");
                    userSessionRepo.delete(userSession);
                    return chatResponse;
                }
                userSession.setMessage(chat.getMessage());
                // store debit card
                userSession.setCurrent_status("DEBIT_PIN");
                userSessionRepo.save(userSession);

                chatResponse.setReply("Enter Debit Card PIN:");
                return chatResponse;
            }

            /* STEP 3: Receive Debit PIN */
            if (userSession.getCurrent_status().equals("DEBIT_PIN")) {
                AccInformation accInformation = accInformationRepo.
                        findById(userSession.getPhone_number())
                        .orElse(null);
                if(!accInformation.getDebitCard_Pin().equals(chat.getMessage())){
                    chatResponse.setReply("your debitcard_PIN is Wrong enter try again");
                    userSessionRepo.delete(userSession);
                    return chatResponse;
                }

                userSession.setCurrent_status("SET_PIN");
                userSessionRepo.save(userSession);

                chatResponse.setReply("Set Wallet PIN:");
                return chatResponse;
            }

            /* STEP 4: Set Wallet PIN */
            if (userSession.getCurrent_status().equals("SET_PIN")) {

                userSession.setMessage(chat.getMessage());
                userSession.setCurrent_status("CONFIRM_PIN");
                userSessionRepo.save(userSession);
                chatResponse.setReply("Confirm Wallet PIN:");
                return chatResponse;
            }

            /* STEP 5: Confirm Wallet PIN */
            if (userSession.getCurrent_status().equals("CONFIRM_PIN")) {

                if (!userSession.getMessage().equals(chat.getMessage())) {
                    chatResponse.setReply("PIN mismatch ❌\nSet Wallet PIN again:");
                    userSession.setCurrent_status("SET_PIN");
                    userSessionRepo.save(userSession);
                    return chatResponse;
                }

                Wallet wallet = new Wallet();
                wallet.setPhonenumber(chat.getPhone());
                wallet.setPin(chat.getMessage());
                wallet.setBalance(0);
                AccInformation acc=accInformationRepo.findById(chat.getPhone()).orElse(null);
                wallet.setAccInformation(acc);
                walletRepo.save(wallet);
                userSessionRepo.delete(userSession);
                chatResponse.setReply("🎉 Registration Successful!\nYour wallet is active.");
                return chatResponse;
            }
        }
        chatResponse.setReply("you are already registered please select another option");
        return chatResponse;
    }
}