package com.farmermart.notification.Notification.Service.controller;

import com.farmermart.notification.Notification.Service.dto.request.NotificationRequestDto;
import com.farmermart.notification.Notification.Service.dto.response.NotificationResponseDto;
import com.farmermart.notification.Notification.Service.model.NotificationChannel;
import com.farmermart.notification.Notification.Service.model.NotificationContent;
import com.farmermart.notification.Notification.Service.model.NotificationStatus;
import com.farmermart.notification.Notification.Service.services.impl.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final NotificationServiceImpl notificationService;


    @PostMapping("/payment")
    public NotificationResponseDto sendPaymentNotification(
            @RequestParam String userId,
            @RequestParam boolean success,
            @RequestParam NotificationChannel channel) {

        String message = success
                ? "Your payment was successful. Thank you for your purchase!"
                : "Your payment failed. Please try again or contact support.";

        NotificationRequestDto request = NotificationRequestDto.builder()
                .userId(userId)
                .message(message)
                .content(NotificationContent.TRANSACTION_ALERT)
                .channel(channel)
                .build();

        NotificationStatus status = success ? NotificationStatus.SENT : NotificationStatus.FAILED;

        notificationService.saveNotification(request, status);
        return sendByChannel(request);
    }

    @PostMapping("/product-not-found")
    public NotificationResponseDto sendProductNotFoundNotification(
            @RequestParam String userId,
            @RequestParam NotificationChannel channel) {

        NotificationRequestDto request = NotificationRequestDto.builder()
                .userId(userId)
                .message("Sorry, the requested product could not be found.")
                .content(NotificationContent.TRANSACTION_ALERT)
                .channel(channel)
                .build();

        return sendByChannel(request);
    }


    @PostMapping("/product-quality-issue/{userId}/{channel}")
    public NotificationResponseDto sendProductQualityNotification(
            @PathVariable String userId,
            @PathVariable NotificationChannel channel) {

        NotificationRequestDto request = NotificationRequestDto.builder()
                .userId(userId)
                .message("The product reported by vendor is not in good condition. Please review it.")
                .content(NotificationContent.TRANSACTION_ALERT)
                .channel(channel)
                .build();

        return sendByChannel(request);
    }

    private NotificationResponseDto sendByChannel(NotificationRequestDto request) {
        switch (request.getChannel()) {
            case EMAIL:
                return notificationService.sendEmailNotification(request);
            case SMS:
                return notificationService.sendSmsNotification(request);
            case IN_APP:
                return notificationService.sendInAppNotification(request);
            default:
                throw new IllegalArgumentException("Unsupported channel: " + request.getChannel());
        }
    }
}
