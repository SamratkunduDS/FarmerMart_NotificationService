package com.farmermart.notification.Notification.Service.consume;

import com.farmermart.notification.Notification.Service.dto.request.PromotionRequestDto;
import com.farmermart.notification.Notification.Service.services.channel.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaListenService {

    private final PromotionService promotionService;

    @KafkaListener(topics = "promotionTopic", groupId = "notificationGroup")
    public void consumePromotion(PromotionRequestDto promotionRequestDto){
        System.out.println("Consumed promotion: " + promotionRequestDto);
        promotionService.createPromotion(promotionRequestDto);
    }
}
