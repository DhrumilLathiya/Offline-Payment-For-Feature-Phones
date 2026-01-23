package org.example.offlinebackend.Service;

import org.example.offlinebackend.Model.PaymentToken;
import org.example.offlinebackend.Model.Wallet;
import org.example.offlinebackend.Repo.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private TokenRepo tokenRepo;

    public PaymentToken generateToken(
            Wallet sender,
            String receiverMobileNumber,
            int amount
    ) {

        PaymentToken token = new PaymentToken();
        token.setTokenId("TXN-" + UUID.randomUUID().toString().substring(0, 8));
        token.setAmount(amount);
        token.setReceiverMobile(receiverMobileNumber);
        token.setStatus("CREATED");
        token.setSenderMobile(sender.getPhonenumber());
        token.setSenderWallet(sender);
        tokenRepo.save(token);
        return token;
    }
}

