package org.example.offlinebackend.Model.Dto;

import lombok.Data;

@Data
public class PaymentTokenDTO {
    private String tokenId;
    private String senderMobile;
    private String receiverMobile;
    private int amount;
}
