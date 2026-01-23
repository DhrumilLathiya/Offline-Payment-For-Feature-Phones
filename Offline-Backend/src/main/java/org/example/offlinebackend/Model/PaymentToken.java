package org.example.offlinebackend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class PaymentToken {

    @Id
    private String tokenId;

    private String senderMobile;
    private String receiverMobile;
    private int amount;
    private String status;
    @ManyToOne
    @JoinColumn(name = "sender_wallet_id")
    private Wallet senderWallet;
}
