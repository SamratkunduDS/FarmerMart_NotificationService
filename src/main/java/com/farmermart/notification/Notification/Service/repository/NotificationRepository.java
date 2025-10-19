package com.farmermart.notification.Notification.Service.repository;

import com.farmermart.notification.Notification.Service.model.entity.Notifications;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notifications, String> {
}
