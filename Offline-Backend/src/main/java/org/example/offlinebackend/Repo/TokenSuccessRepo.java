package org.example.offlinebackend.Repo;

import org.example.offlinebackend.Model.PaymentTokenSuccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenSuccessRepo
        extends JpaRepository<PaymentTokenSuccess, String> {

}

