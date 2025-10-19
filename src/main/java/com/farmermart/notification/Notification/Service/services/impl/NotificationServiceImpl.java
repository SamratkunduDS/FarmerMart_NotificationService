package com.farmermart.notification.Notification.Service.services.impl;

import com.farmermart.notification.Notification.Service.dto.request.NotificationRequestDto;
import com.farmermart.notification.Notification.Service.model.entity.Notifications;
import com.farmermart.notification.Notification.Service.services.channel.PromotionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationServiceImpl implements PromotionService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createPromotion(NotificationRequestDto notificationRequestDto) {
        Notifications map = modelMapper.map(notificationRequestDto, Notifications.class);
    }
}
