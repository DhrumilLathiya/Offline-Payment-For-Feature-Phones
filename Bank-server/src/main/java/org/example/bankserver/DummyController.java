package org.example.bankserver;

import org.example.bankserver.Model.BankTopupDTO;
import org.example.bankserver.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.example.bankserver.Model.BankUser;
import org.example.bankserver.Repo.BankUserRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dummy-bank")
public class DummyController {

    private final BankUserRepo bankUserRepo;

    public DummyController(BankUserRepo bankUserRepo) {
        this.bankUserRepo = bankUserRepo;
    }

    @Autowired
    org.example.bankserver.Util.JwtUtil jwtUtil;

    @PostMapping("/verify")
    public org.springframework.http.ResponseEntity<?> verify(
            @RequestBody PaymentTokenDTO[] tokens,
            @org.springframework.web.bind.annotation.RequestHeader(value = "Authorization", required = false) String authHeader
    ) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return org.springframework.http.ResponseEntity.status(401).body("Missing or invalid Authorization header");
        }

        String tokenStr = authHeader.substring(7);
        if (!jwtUtil.validateToken(tokenStr)) {
            return org.springframework.http.ResponseEntity.status(401).body("Invalid Token");
        }


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

            boolean flag=false;
            if(flag==true){
                res.setStatus("SUCCESS");
                receiver.setBalance(receiver.getBalance() + token.getAmount());
                bankUserRepo.save(receiver);
            }else {
                res.setStatus("FAILED");
            }
            responses[i] = res;
        }
        return org.springframework.http.ResponseEntity.ok(responses);
    }

    @PostMapping("/topup")
    public void topup(
            @RequestBody BankTopupDTO dto,
            @RequestHeader("Authorization") String auth
    ) {
        jwtUtil.validateToken(auth.substring(7));

        BankUser user = bankUserRepo.findById(dto.getPhoneNo()).get();

        user.setBalance(user.getBalance() - dto.getAmount());
        bankUserRepo.save(user);
    }
}
