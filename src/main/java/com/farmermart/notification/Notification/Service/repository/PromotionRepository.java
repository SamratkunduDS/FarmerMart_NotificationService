package com.farmermart.notification.Notification.Service.repository;

import com.farmermart.notification.Notification.Service.model.entity.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends MongoRepository<Promotion,String> {
}
