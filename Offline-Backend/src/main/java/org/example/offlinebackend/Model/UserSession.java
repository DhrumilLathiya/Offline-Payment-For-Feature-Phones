package org.example.offlinebackend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="UserSession")
public class UserSession {
    @Id
    String phone_number;
    String current_status;
    String message;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
