package org.example.offlinebackend.Model.Dto;

public class BankTopupDTO {
    private String phoneNo;
    private int amount;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    // getters & setters
}
