package org.example.offlinebackend.Service;

import org.example.offlinebackend.Model.*;
import org.example.offlinebackend.Repo.ACCInformationRepo;
import org.example.offlinebackend.Repo.UserSessionRepo;
import org.example.offlinebackend.Repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
@Service
public class ChatService {

    @Autowired
    UserSessionRepo userSessionRepo;

    @Autowired
    WalletRepo walletRepo;

    @Autowired
    RegisterService registerService;

    @Autowired
    WalletService walletService;

    @Autowired
    TopupService topupService;

    @Autowired
    ResetPin resetPin;

    @Autowired
    CheckBalanceService checkBalanceService;

    public ChatResponse ChatHandel(Chat chat) {

        UserSession session=userSessionRepo.findById(chat.getPhone()).orElse(null);
        if(session==null){
            UserSession session1=new UserSession();
            session1.setPhone_number(chat.getPhone());
            userSessionRepo.save(session1);
            return new ChatResponse(getMainMenu());
        }

        // First-time user → show menu
        if (session.getUser_status() == null) {
            session.setUser_status(chat.getMessage());
            userSessionRepo.save(session);
        }

        String option = session.getUser_status();

        // Wallet existence check

        if ("1".equals(option)) {
            return registerService.Register(session, chat);
        }

        if ("2".equals(option)) {
            return walletService.Transaction(session, chat);
        }

        if ("3".equals(option)) {
            //return topupService.handle(session, chat);
        }

        if ("4".equals(option)) {
        //    return resetPin.handle(session, chat);
        }

        if ("5".equals(option)) {
           // return checkBalanceService.handle(session, chat);
        }
        return new ChatResponse("verdict");
    }

    private String getMainMenu() {
        return "Welcome to Offline Wallet Service\n" +
                "--------------------------------\n" +
                "1. Register\n" +
                "2. Money Transfer\n" +
                "3. Top Up\n" +
                "4. Reset PIN\n" +
                "5. Check Balance\n" +
                "Reply with option number:";
    }
}
