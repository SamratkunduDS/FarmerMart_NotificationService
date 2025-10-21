package com.farmermart.notification.Notification.Service.dto.response;

import com.farmermart.notification.Notification.Service.model.NotificationChannel;
import com.farmermart.notification.Notification.Service.model.NotificationContent;
import com.farmermart.notification.Notification.Service.model.NotificationStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NotificationResponseDto {
    private String type;
    private NotificationChannel channels;
    private NotificationContent content;
    private NotificationStatus status;
    private String message;
    private LocalDateTime createdAt;
}
