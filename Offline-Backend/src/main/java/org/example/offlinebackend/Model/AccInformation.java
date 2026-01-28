package org.example.offlinebackend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Account_information")
public class AccInformation {
    @Id
    String id;
    String debitCard_number;
    String debitCard_Pin;
}