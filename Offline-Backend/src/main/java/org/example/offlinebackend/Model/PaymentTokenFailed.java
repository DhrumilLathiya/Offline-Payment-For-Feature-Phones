package org.example.offlinebackend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment_token_failed")
public class PaymentTokenFailed {

    @Id
    private String tokenId;
    private String senderMobile;
    private String receiverMobile;
    private int amount;
    private String status;

    private LocalDateTime failedAt;
}
