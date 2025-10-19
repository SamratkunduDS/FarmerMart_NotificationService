package com.farmermart.notification.Notification.Service.services.impl;

import com.farmermart.notification.Notification.Service.dto.request.PromotionRequestDto;
import com.farmermart.notification.Notification.Service.dto.response.PromoOffer;
import com.farmermart.notification.Notification.Service.model.entity.Notifications;
import com.farmermart.notification.Notification.Service.model.entity.Promotion;
import com.farmermart.notification.Notification.Service.repository.PromotionRepository;
import com.farmermart.notification.Notification.Service.services.channel.PromotionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements PromotionService {

    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final PromotionRepository promotionRepository;

    @Override
    public PromoOffer createPromotion(PromotionRequestDto promotionRequestDto) {
        Promotion map = modelMapper.map(promotionRequestDto, Promotion.class);
        Promotion save = promotionRepository.save(map);
        return new PromoOffer(save.getPromoCode(), save.getDescription(),map.getValidFrom());
    }
}
