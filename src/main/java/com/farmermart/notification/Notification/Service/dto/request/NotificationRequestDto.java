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
public class NotificationRequestDto {
    private String id;
    private String userId;
    private String message;
    private String channel;
    private String content;
    private String status;
    private Time createdAt;
    private Time updatedAt;
}
