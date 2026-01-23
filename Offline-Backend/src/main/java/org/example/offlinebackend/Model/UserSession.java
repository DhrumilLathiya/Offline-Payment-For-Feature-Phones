package org.example.offlinebackend.Model;

import jakarta.persistence.*;
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
    private String phone_number;

    private String current_status;
    String message;
    private Integer amount;
    private String receiver_mobile;
    private Integer pin_attempts;

    private String user_status;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
