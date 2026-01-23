package org.example.offlinebackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="wallet_id")
    Long id;
    int balance;
    String phonenumber;
    String pin;
    String status;
    @OneToOne(cascade = CascadeType.ALL)
    AccInformation accInformation;
    @OneToMany(mappedBy = "senderWallet", cascade = CascadeType.ALL)
    private List<PaymentToken> tokens = new ArrayList<>();
}
