package com.farmermart.notification.Notification.Service.model;

import lombok.Data;

//@Data
//public class OtpVerifyRequest {
//    private String email;
//    private String otp;
//}


public class OtpVerifyRequest {
    private String identifier; // email or phone
    private String otp;

    // getters and setters
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}