package org.example.offlinebackend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMobile {

    @JsonProperty("phoneNo")
    String PhoneNo;

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }
}
