package org.example.offlinebackend.Controller;

import org.example.offlinebackend.Model.Chat;
import org.example.offlinebackend.Model.ChatResponse;
import org.example.offlinebackend.Model.UserMobile;
import org.example.offlinebackend.Service.ChatService;
import org.example.offlinebackend.Service.WalletSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("token/success")
    public void tokenSuccess(@RequestBody UserMobile userMobile) {
    }

    @GetMapping("token/failure")
    public void GetChat(@RequestBody UserMobile userMobile) {
    }

}
