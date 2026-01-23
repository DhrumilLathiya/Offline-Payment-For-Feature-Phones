package org.example.offlinebackend.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.*;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@RestController
public class SmsWebSocketHandler extends TextWebSocketHandler {

        @Override
        protected void handleTextMessage(
                WebSocketSession session,
                TextMessage message
        ) throws IOException {

            String userSms = message.getPayload();

            System.out.println("📩 SMS RECEIVED: " + userSms);

            // ---- SMS LOGIC ----
            String reply;

            if (userSms.equalsIgnoreCase("Bank Transaction")) {
                reply = "Welcome!\n1. Register\n2. Pay Offline\nReply with option";
            } else if (userSms.equals("1")) {
                reply = "Registration selected.\nVisit bank for onboarding.";
            } else if (userSms.equals("2")) {
                reply = "Enter merchant ID:";
            } else {
                reply = "Invalid input. Type: Bank Transaction";
            }

            // ---- SEND SMS BACK ----
            session.sendMessage(new TextMessage(reply));
        }

}
