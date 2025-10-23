package com.farmermart.notification.Notification.Service.services;

import com.farmermart.notification.Notification.Service.dto.request.NotificationRequestDto;
import com.farmermart.notification.Notification.Service.dto.response.NotificationResponseDto;
import com.farmermart.notification.Notification.Service.model.NotificationChannel;
import com.farmermart.notification.Notification.Service.model.NotificationContent;
import com.farmermart.notification.Notification.Service.model.NotificationStatus;
import com.farmermart.notification.Notification.Service.model.entity.Notifications;
import com.farmermart.notification.Notification.Service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminNotificationServiceImpl implements AdminNotificationService{

    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;

    @Override
    public NotificationResponseDto sendProductDeletedAlert(NotificationRequestDto request) {
        Notifications notification = modelMapper.map(request, Notifications.class);
       notification.setMessage("Product deleted by admin. Product ID: " + request.getUserId());
        notification.setContent(NotificationContent.ADMIN_ACTION);
        notification.setChannel(NotificationChannel.IN_APP);
        notification.setStatus(NotificationStatus.SENT);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());

        Notifications saved = notificationRepository.save(notification);
        return modelMapper.map(saved, NotificationResponseDto.class);
    }

    @Override
    public NotificationResponseDto sendProductUpdatedAlert(NotificationRequestDto request) {
        Notifications notification = modelMapper.map(request, Notifications.class);
        notification.setMessage(" Product updated by admin. Product ID: " + request.getUserId());
        notification.setContent(NotificationContent.ADMIN_ACTION);
        notification.setChannel(NotificationChannel.IN_APP);
        notification.setStatus(NotificationStatus.SENT);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());
        Notifications saved = notificationRepository.save(notification);
        return modelMapper.map(saved, NotificationResponseDto.class);
    }
    @Override
    public NotificationResponseDto sendVendorDeletionAlert(NotificationRequestDto request) {
        Notifications notification = modelMapper.map(request, Notifications.class);
        notification.setMessage(" Vendor deleted by admin. Vendor ID: " + request.getUserId());
        notification.setContent(NotificationContent.ADMIN_ACTION);
        notification.setChannel(NotificationChannel.IN_APP);
        notification.setStatus(NotificationStatus.SENT);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());
        Notifications saved = notificationRepository.save(notification);
        return modelMapper.map(saved, NotificationResponseDto.class);
    }
    @Override
    public NotificationResponseDto sendUserBlockedAlert(NotificationRequestDto request) {
        Notifications notification = modelMapper.map(request, Notifications.class);
       notification.setMessage("User blocked by admin. User ID: " + request.getUserId());
        notification.setContent(NotificationContent.ADMIN_ACTION);
        notification.setChannel(NotificationChannel.IN_APP);
        notification.setStatus(NotificationStatus.SENT);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());
        Notifications saved = notificationRepository.save(notification);
        return modelMapper.map(saved, NotificationResponseDto.class);
    }
    @Override
    public NotificationResponseDto sendAdminActionAlert(NotificationRequestDto request) {
        Notifications notifications = modelMapper.map(request, Notifications.class);
        notifications.setMessage("🛠️ Admin performed a general action.");
        notifications.setContent(NotificationContent.ADMIN_ACTION);
        notifications.setChannel(NotificationChannel.IN_APP);
        notifications.setStatus(NotificationStatus.SENT);
        notifications.setCreatedAt(LocalDateTime.now());
        notifications.setUpdatedAt(LocalDateTime.now());
        Notifications saved = notificationRepository.save(notifications);
        return modelMapper.map(saved, NotificationResponseDto.class);
    }

}
