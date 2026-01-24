package org.example.bankserver.Config;

import org.example.bankserver.Model.BankUser;
import org.example.bankserver.Repo.BankUserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(BankUserRepo repository) {
        return args -> {
            BankUser user = new BankUser();
            user.setMobileNumber("9876543210");
            user.setBalance(5000);
            repository.save(user);
            System.out.println("Seeded User: 9876543210 with 5000 balance");
        };
    }
}
