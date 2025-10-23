package com.farmermart.notification.Notification.Service.dto.event;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEvent {
    private String productId;
    private String productName;
    private String vendorId;
    private String vendorName;
    private String type;
    private String message;
}
