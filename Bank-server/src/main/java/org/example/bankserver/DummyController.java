package org.example.bankserver;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/dummy-bank")
public class DummyController {
    @PostMapping("/verify")
    public BankResponse[] verify(
            @RequestBody PaymentTokenDTO[] tokens
    ) {
        System.out.println("hello");
        BankResponse[] responses = new BankResponse[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            BankResponse res = new BankResponse();
            res.setTokenId(tokens[i].getTokenId());
            boolean success = Math.random() > 0.2;

            res.setStatus(success ? "SUCCESS" : "FAILED");
            responses[i] = res;
        }
        return responses;
    }
}