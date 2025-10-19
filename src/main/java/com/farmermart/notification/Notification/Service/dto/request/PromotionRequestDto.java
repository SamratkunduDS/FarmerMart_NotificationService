package com.farmermart.notification.Notification.Service.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PromotionRequestDto {


    private String promoCode;
    private String description;
    private LocalDateTime validFrom;
    private LocalDateTime validTill;

    private boolean active;
}
