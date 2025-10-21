package com.farmermart.notification.Notification.Service.services;

import com.farmermart.notification.Notification.Service.dto.request.NotificationRequestDto;
import com.farmermart.notification.Notification.Service.dto.response.NotificationResponseDto;
import com.farmermart.notification.Notification.Service.model.NotificationStatus;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    NotificationResponseDto saveNotification(NotificationRequestDto request, NotificationStatus status);
    NotificationResponseDto sendInAppNotification(NotificationRequestDto request);
    public NotificationResponseDto sendPaymentNotification(String paymentId);
}
