package com.farmermart.notification.Notification.Service.services;

import com.farmermart.notification.Notification.Service.dto.request.NotificationRequestDto;
import com.farmermart.notification.Notification.Service.dto.response.NotificationResponseDto;

public interface AdminNotificationService {
    NotificationResponseDto sendVendorDeletionAlert(NotificationRequestDto request);

    NotificationResponseDto sendUserBlockedAlert(NotificationRequestDto request);

    NotificationResponseDto sendAdminActionAlert(NotificationRequestDto request);

    NotificationResponseDto sendProductDeletedAlert(NotificationRequestDto request);

    NotificationResponseDto sendProductUpdatedAlert(NotificationRequestDto request);
}
