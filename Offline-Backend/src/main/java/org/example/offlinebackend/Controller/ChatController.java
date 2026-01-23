package org.example.offlinebackend.Controller;

import org.example.offlinebackend.Model.Chat;
import org.example.offlinebackend.Model.ChatResponse;
import org.example.offlinebackend.Service.ChatService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:5173")
public class ChatController {

    ChatService chatService;
    @PostMapping("api/chat")
     public ChatResponse sendMessage(@RequestBody Chat chat) {

        return chatService.ChatHandel(chat);
    }
}
