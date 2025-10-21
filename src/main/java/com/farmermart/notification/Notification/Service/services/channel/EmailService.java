package com.farmermart.notification.Notification.Service.services.channel;

import com.farmermart.notification.Notification.Service.dto.request.NotificationRequestDto;
import com.farmermart.notification.Notification.Service.dto.response.NotificationResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    NotificationResponseDto sendEmailNotification(NotificationRequestDto request);
}
