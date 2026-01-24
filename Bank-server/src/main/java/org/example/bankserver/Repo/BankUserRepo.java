package org.example.bankserver.Repo;

import org.example.bankserver.Model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankUserRepo extends JpaRepository<BankUser, String> {
}
