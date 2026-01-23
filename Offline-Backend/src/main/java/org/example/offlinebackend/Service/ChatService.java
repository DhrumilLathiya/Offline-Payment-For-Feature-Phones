package org.example.offlinebackend.Service;

import org.example.offlinebackend.Model.Chat;
import org.example.offlinebackend.Model.ChatResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ChatService {
    public ChatResponse ChatHandel(@RequestBody Chat chat) {



        return new ChatResponse("");

    }
}
