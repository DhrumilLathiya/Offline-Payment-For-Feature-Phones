package org.example.offlinebackend.Repo;

import org.example.offlinebackend.Model.PaymentTokenSuccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenSuccessRepo
        extends JpaRepository<PaymentTokenSuccess, String> {
    List<PaymentTokenSuccess> findBySenderMobile(String phoneNo);
}

