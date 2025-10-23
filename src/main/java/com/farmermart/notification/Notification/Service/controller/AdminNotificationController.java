package com.farmermart.notification.Notification.Service.controller;

import com.farmermart.notification.Notification.Service.dto.request.NotificationRequestDto;
import com.farmermart.notification.Notification.Service.dto.response.NotificationResponseDto;
import com.farmermart.notification.Notification.Service.model.NotificationChannel;
import com.farmermart.notification.Notification.Service.services.AdminNotificationService;
import com.farmermart.notification.Notification.Service.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/notifications")
@RequiredArgsConstructor
public class AdminNotificationController {

    @Autowired
    private final AdminNotificationService adminNotificationService;

    @PostMapping("/product/delete")
    public ResponseEntity<NotificationResponseDto> sendProductDeletedAlert(@RequestBody NotificationRequestDto request) {
        return ResponseEntity.ok(adminNotificationService.sendProductDeletedAlert(request));
    }
    @PostMapping("/vendor/delete")
    public ResponseEntity<NotificationResponseDto> sendVendorDeletionAlert(@RequestBody NotificationRequestDto request) {
        return ResponseEntity.ok(adminNotificationService.sendVendorDeletionAlert(request));
    }
    @PostMapping("/user/block")
    public ResponseEntity<NotificationResponseDto>  sendUserBlockedAlert(@RequestBody NotificationRequestDto request) {
        return ResponseEntity.ok(adminNotificationService.sendUserBlockedAlert(request));
    }

}
