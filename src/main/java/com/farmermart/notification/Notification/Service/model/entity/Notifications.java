package com.farmermart.notification.Notification.Service.model.entity;

import com.farmermart.notification.Notification.Service.model.NotificationChannel;
import com.farmermart.notification.Notification.Service.model.NotificationContent;
import com.farmermart.notification.Notification.Service.model.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Notifications {
    @Id
    private String id;
    private String userId;
    private String message;
    private NotificationChannel channel;
    private NotificationContent content;
    private NotificationStatus status;
    private Time createdAt;
    private Time updatedAt;
}
