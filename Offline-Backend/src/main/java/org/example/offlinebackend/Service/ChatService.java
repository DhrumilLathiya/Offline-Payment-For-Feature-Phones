package org.example.offlinebackend.Service;

import org.example.offlinebackend.Model.Chat;
import org.example.offlinebackend.Model.ChatResponse;
import org.example.offlinebackend.Model.UserSession;
import org.example.offlinebackend.Model.Wallet;
import org.example.offlinebackend.Repo.UserSessionRepo;
import org.example.offlinebackend.Repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ChatService {
    @Autowired
    UserSessionRepo userSessionRepo;
    @Autowired
    WalletRepo walletRepo;
    public ChatResponse ChatHandel(@RequestBody Chat chat) {
        ChatResponse chatResponse=new ChatResponse();
        UserSession userSession = new UserSession();
        userSession.setPhone_number(chat.getPhone());
        userSessionRepo.save(userSession);

        Wallet wallet=walletRepo.findByphonenumber(chat.getPhone());

        
        if(chat.getMessage()=="1"){
            if(userSession.getCurrent_status()==null){
                if(wallet!=null) {
                    chatResponse.setReply("you are already in wallet");
                    return chatResponse;
                } else {
                    chatResponse.setReply("Enter Debit Card Number");
                    userSession.setCurrent_status("ENTER_CARD");
                    userSessionRepo.save(userSession);
                }
            }
        }





        return chatResponse;
    }
}
