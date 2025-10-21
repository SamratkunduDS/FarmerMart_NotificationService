package com.farmermart.notification.Notification.Service.services.channel;


import com.farmermart.notification.Notification.Service.dto.request.PromotionRequestDto;
import com.farmermart.notification.Notification.Service.dto.response.PromoOffer;
import org.springframework.stereotype.Service;

@Service
public interface PromotionService {

    PromoOffer createPromotion(PromotionRequestDto promotionRequestDto);
    public PromotionRequestDto getAll();

    PromoOffer getPromoCode(String promoCode);

    void deleteByid(String id);
}
