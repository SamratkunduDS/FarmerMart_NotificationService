package com.farmermart.notification.Notification.Service.dto.request;

import lombok.*;

@Data
@Setter
@Getter
public class OtpRequest {

    private String email;
    private String phoneNumber;


}

