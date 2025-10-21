package com.farmermart.notification.Notification.Service.controller;

import com.farmermart.notification.Notification.Service.dto.request.NotificationRequestDto;
import com.farmermart.notification.Notification.Service.dto.request.PromotionRequestDto;
import com.farmermart.notification.Notification.Service.dto.response.PromoOffer;
import com.farmermart.notification.Notification.Service.services.channel.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/promo")
@RequiredArgsConstructor
public class PromotionalController {
    @Autowired
    private final PromotionService promotionService;

    @PostMapping("/offer")
    public ResponseEntity<PromoOffer> sendPromotionalOffer(@RequestBody PromotionRequestDto promotionRequestDto) {
        PromoOffer promotion = promotionService.createPromotion(promotionRequestDto);
        return new ResponseEntity<PromoOffer>(promotion, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<PromotionRequestDto> getAllPromotions() {
        PromotionRequestDto promotions = promotionService.getAll();
        return ok(promotions);
    }

    @GetMapping("/{promoCode}")
    public ResponseEntity<PromoOffer> getPromotionByCode(@PathVariable("promoCode") String promoCode) {
        PromoOffer promo = promotionService.getPromoCode(promoCode);
        return new ResponseEntity<PromoOffer>(new PromoOffer(promo.getOfferCode(),
                promo.getDescription(),promo.getValidTill()),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id)
    {
        promotionService.deleteByid(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
