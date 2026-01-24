package org.example.offlinebackend.Service;

import org.example.offlinebackend.Model.Dto.BankResponse;
import org.example.offlinebackend.Model.Dto.PaymentTokenDTO;
import org.example.offlinebackend.Model.PaymentToken;
import org.example.offlinebackend.Model.UserMobile;
import org.example.offlinebackend.Model.Wallet;
import org.example.offlinebackend.Repo.TokenRepo;
import org.example.offlinebackend.Repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletSyncService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WalletRepo walletRepo;
    @Autowired
    TokenRepo tokenRepo;
    public String tokenSync(UserMobile userMobile) {

        List<PaymentToken> tokens =
                tokenRepo.findBySenderMobile(userMobile.getPhoneNo());

        if (tokens.isEmpty()) return "NO PENDING";

        List<PaymentTokenDTO> dtoList = new ArrayList<>();
        for (PaymentToken token : tokens) {
            PaymentTokenDTO dto = new PaymentTokenDTO();
            dto.setTokenId(token.getTokenId());
            dto.setSenderMobile(token.getSenderMobile());
            dto.setReceiverMobile(token.getReceiverMobile());
            dto.setAmount(token.getAmount());
            dtoList.add(dto);
        }

        BankResponse[] responses =
                restTemplate.postForObject(
                        "http://localhost:9090/dummy-bank/verify",
                        dtoList,
                        BankResponse[].class
                );

        for (BankResponse res : responses) {
            System.out.println(res.getStatus());
            PaymentToken token =
                    tokenRepo.findById(res.getTokenId()).orElse(null);

            if (token == null) continue;

            if ("FAILED".equals(res.getStatus())) {
                Wallet sender =
                        walletRepo.findByphonenumber(token.getSenderMobile());
                sender.setBalance(sender.getBalance() + token.getAmount());
                walletRepo.save(sender);
            }
            token.setStatus(res.getStatus());
            tokenRepo.save(token);
        }

        return "SUCCESS";
    }

}
