package org.example.bankserver;

import org.example.bankserver.Model.BankUser;
import org.example.bankserver.Repo.BankUserRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/dummy-bank")
public class DummyController {

    private final BankUserRepo bankUserRepo;

    public DummyController(BankUserRepo bankUserRepo) {
        this.bankUserRepo = bankUserRepo;
    }

    @PostMapping("/verify")
    public BankResponse[] verify(@RequestBody PaymentTokenDTO[] tokens) {

        BankResponse[] responses = new BankResponse[tokens.length];

        for (int i = 0; i < tokens.length; i++) {

            PaymentTokenDTO token = tokens[i];
            BankResponse res = new BankResponse();
            res.setTokenId(token.getTokenId());

            BankUser receiver =
                    bankUserRepo.findById(token.getReceiverMobile()).orElse(null);

            if (receiver == null) {
                res.setStatus("FAILED");
                responses[i] = res;
                continue;
            }


            receiver.setBalance(receiver.getBalance() + token.getAmount());
            bankUserRepo.save(receiver);

            res.setStatus("SUCCESS");
            responses[i] = res;
        }
        return responses;
    }
}
