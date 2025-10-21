package com.farmermart.notification.Notification.Service.consume;

import com.farmermart.notification.Notification.Service.dto.event.ProductEvent;
import com.farmermart.notification.Notification.Service.dto.event.UserEvent;
import com.farmermart.notification.Notification.Service.dto.request.PromotionRequestDto;
import com.farmermart.notification.Notification.Service.dto.response.PaymentResponse;
import com.farmermart.notification.Notification.Service.dto.response.UserResponse;
import com.farmermart.notification.Notification.Service.services.channel.PromotionService;
import com.farmermart.notification.Notification.Service.services.impl.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaListenService {

    private final NotificationServiceImpl notificationService;

    @KafkaListener(topics = "promotionTopic", groupId = "notificationGroup")
    public void consumePromotion(PromotionRequestDto promotionRequestDto){
        System.out.println("Consumed promotion: " + promotionRequestDto);
        notificationService.createPromotion(promotionRequestDto);
    }

    @KafkaListener(topics = "payment-topic", groupId = "notification-group")
    public void consumePaymentEvent(PaymentResponse event) {
        System.out.println("Received payment event: " + event);
        notificationService.sendPaymentNotification(event.getPaymentId());
    }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void consumeUserEvent(UserEvent event) {
        switch (event.getType()) {
            case "VENDOR_DELETED" -> notificationService.sendVendorDeletionAlert(event);
            case "USER_BLOCKED" -> notificationService.sendUserBlockedAlert(event);
            case "ADMIN_ACTION" -> notificationService.sendAdminActionAlert(event);
            default -> System.out.println("⚠️ Unknown user event: " + event.getType());
        }
    }

    // 🔸 Listen for product-related events
    @KafkaListener(topics = "product-events", groupId = "notification-group")
    public void consumeProductEvent(ProductEvent event) {
        switch (event.getType()) {
            case "PRODUCT_DELETED" -> notificationService.sendProductDeletedAlert(event);
            case "PRODUCT_UPDATED" -> notificationService.sendProductUpdatedAlert(event);
            default -> System.out.println("⚠️ Unknown product event: " + event.getType());
        }
    }

}
