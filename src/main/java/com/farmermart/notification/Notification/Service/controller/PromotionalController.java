package com.farmermart.notification.Notification.Service.controller;

import com.farmermart.notification.Notification.Service.dto.request.NotificationRequestDto;
import com.farmermart.notification.Notification.Service.dto.response.PromoOffer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promo")
public class PromotionalController {


    @PostMapping("/offer")
    public ResponseEntity<PromoOffer> sendPromotionalOffer(@RequestBody NotificationRequestDto notificationRequestDto) {
        // Logic to send promotional offer
        PromoOffer promoOffer = new PromoOffer("SAVE20", "Get 20% off on your next purchase!", "2024-12-31");
        return ResponseEntity.ok(promoOffer);
    }

//    public

}
