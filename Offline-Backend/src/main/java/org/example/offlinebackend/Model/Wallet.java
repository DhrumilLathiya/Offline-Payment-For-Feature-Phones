package org.example.offlinebackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    int reserved_balance;
    String phonenumber;
    int pin;
    String status;
    @OneToOne(cascade = CascadeType.ALL)
    AccInformation accInformation;
}
