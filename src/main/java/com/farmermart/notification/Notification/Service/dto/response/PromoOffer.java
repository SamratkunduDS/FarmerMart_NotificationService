package com.farmermart.notification.Notification.Service.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PromoOffer {
    private String offerCode;
    private String description;
    private LocalDateTime validTill;
}
