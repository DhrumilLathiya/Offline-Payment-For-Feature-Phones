package org.example.offlinebackend.Service;

import org.example.offlinebackend.Model.*;
import org.example.offlinebackend.Model.Dto.BankResponse;
import org.example.offlinebackend.Model.Dto.PaymentTokenDTO;
import org.example.offlinebackend.Repo.TokenFailedRepo;
import org.example.offlinebackend.Repo.TokenRepo;
import org.example.offlinebackend.Repo.TokenSuccessRepo;
import org.example.offlinebackend.Repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
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
    @Autowired
    TokenSuccessRepo successRepo;

    @Autowired
    TokenFailedRepo failedRepo;
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

            PaymentToken token =
                    tokenRepo.findById(res.getTokenId()).orElse(null);

            if (token == null) continue;

            if ("FAILED".equals(res.getStatus())) {

                Wallet sender =
                        walletRepo.findByphonenumber(token.getSenderMobile());
                sender.setBalance(sender.getBalance() + token.getAmount());
                walletRepo.save(sender);


                PaymentTokenFailed failed = new PaymentTokenFailed();
                failed.setTokenId(token.getTokenId());
                failed.setSenderMobile(token.getSenderMobile());
                failed.setReceiverMobile(token.getReceiverMobile());
                failed.setAmount(token.getAmount());
                failed.setStatus("FAILED");
                failed.setFailedAt(LocalDateTime.now());

                failedRepo.save(failed);
            }
            else if ("SUCCESS".equals(res.getStatus())) {

                PaymentTokenSuccess success = new PaymentTokenSuccess();
                success.setTokenId(token.getTokenId());
                success.setSenderMobile(token.getSenderMobile());
                success.setReceiverMobile(token.getReceiverMobile());
                success.setAmount(token.getAmount());
                success.setStatus("SUCCESS");
                success.setSettledAt(LocalDateTime.now());

                successRepo.save(success);
            }
            tokenRepo.delete(token);
        }

        return "SUCCESS";
    }

}
