package org.example.offlinebackend.Repo;

import org.example.offlinebackend.Model.UserSession;
import org.example.offlinebackend.Model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository<Wallet,Integer> {
    Wallet findByphonenumber(String phone);
}
