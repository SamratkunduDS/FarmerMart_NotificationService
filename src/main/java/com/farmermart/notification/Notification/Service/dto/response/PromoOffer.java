package com.farmermart.notification.Notification.Service.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PromoOffer {
    String offerCode;
    String description;
    String validTill;
}
