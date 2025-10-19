package com.farmermart.notification.Notification.Service.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class Promotion {
    @Id
    private String id;

    private String promoCode;
    private String description;
    private LocalDateTime validFrom;
    private LocalDateTime validTill;

    private boolean active;
}
