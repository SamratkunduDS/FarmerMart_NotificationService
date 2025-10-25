package com.farmermart.notification.Notification.Service.dto.request;

import com.farmermart.notification.Notification.Service.model.NotificationChannel;
import com.farmermart.notification.Notification.Service.model.NotificationContent;
import com.farmermart.notification.Notification.Service.model.NotificationStatus;
import lombok.*;

import java.sql.Time;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NotificationRequestDto {
    private String id;
    private String userId;
    private String message;
    private String type;
    private NotificationChannel channel;
    private NotificationContent content;
    private NotificationStatus status;
    private Time createdAt;
    private Time updatedAt;
    private String transactionId;
}
