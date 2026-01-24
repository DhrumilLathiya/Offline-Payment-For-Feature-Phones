package org.example.offlinebackend.Repo;

import org.example.offlinebackend.Model.PaymentTokenFailed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenFailedRepo extends JpaRepository<PaymentTokenFailed, String> {

}
