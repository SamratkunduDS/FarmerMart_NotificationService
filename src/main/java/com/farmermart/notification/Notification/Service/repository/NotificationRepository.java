package com.farmermart.notification.Notification.Service.repository;

import com.farmermart.notification.Notification.Service.model.entity.Notifications;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface NotificationRepository extends MongoRepository<Notifications, String> {
}
