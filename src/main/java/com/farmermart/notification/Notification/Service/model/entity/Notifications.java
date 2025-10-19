package com.farmermart.notification.Notification.Service.model.entity;

import com.farmermart.notification.Notification.Service.model.NotificationChannel;
import com.farmermart.notification.Notification.Service.model.NotificationContent;
import com.farmermart.notification.Notification.Service.model.NotificationStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;

@Document
public class Notifications {
    @Id
    private String id;
    private String userId;
    private String type;
    private NotificationChannel channel;
    private NotificationContent content;
    private NotificationStatus status;
    private Time createdAt;
    private Time updatedAt;
}
