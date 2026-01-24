package org.example.offlinebackend.Controller;

import org.example.offlinebackend.Model.Chat;
import org.example.offlinebackend.Model.ChatResponse;
import org.example.offlinebackend.Model.UserMobile;
import org.example.offlinebackend.Service.ChatService;
import org.example.offlinebackend.Service.WalletSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:5173")
public class ChatController {

    @Autowired
    WalletSyncService  walletSyncService;
    @Autowired
    ChatService chatService;
    @PostMapping("api/chat")
     public ChatResponse sendMessage(@RequestBody Chat chat) {
        System.out.println("Hello World");
        return chatService.ChatHandel(chat);
    }

    @PostMapping("user/sync")
    public void syncMessage(@RequestBody UserMobile userMobile) {
        System.out.println(userMobile.getPhoneNo());
        walletSyncService.tokenSync(userMobile);
    }

}
