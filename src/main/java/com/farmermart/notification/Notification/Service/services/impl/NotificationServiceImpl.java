package com.farmermart.notification.Notification.Service.services.impl;

import com.farmermart.notification.Notification.Service.client.PaymentClient;
import com.farmermart.notification.Notification.Service.client.UserClient;
import com.farmermart.notification.Notification.Service.dto.request.NotificationRequestDto;
import com.farmermart.notification.Notification.Service.dto.request.PromotionRequestDto;
import com.farmermart.notification.Notification.Service.dto.response.NotificationResponseDto;
import com.farmermart.notification.Notification.Service.dto.response.PaymentResponse;
import com.farmermart.notification.Notification.Service.dto.response.PromoOffer;
import com.farmermart.notification.Notification.Service.dto.response.UserResponse;
import com.farmermart.notification.Notification.Service.exception.PromoCodeNotExistException;
import com.farmermart.notification.Notification.Service.exception.TemplateLoadException;
import com.farmermart.notification.Notification.Service.model.NotificationChannel;
import com.farmermart.notification.Notification.Service.model.NotificationContent;
import com.farmermart.notification.Notification.Service.model.NotificationStatus;
import com.farmermart.notification.Notification.Service.model.entity.Notifications;
import com.farmermart.notification.Notification.Service.model.entity.Promotion;
import com.farmermart.notification.Notification.Service.repository.NotificationRepository;
import com.farmermart.notification.Notification.Service.repository.PromotionRepository;
import com.farmermart.notification.Notification.Service.services.NotificationService;
import com.farmermart.notification.Notification.Service.services.channel.EmailService;
import com.farmermart.notification.Notification.Service.services.channel.PromotionService;
import com.farmermart.notification.Notification.Service.services.channel.SmsService;
import com.farmermart.notification.Notification.Service.util.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements PromotionService, EmailService, SmsService {


    private final ModelMapper modelMapper;

    private final NotificationRepository notificationRepository;

    private final PromotionRepository promotionRepository;

    private final TemplateUtil templateUtil;

    private final UserClient userClient;

    private final PaymentClient paymentClient;

    @Override
    public PromoOffer createPromotion(PromotionRequestDto promotionRequestDto) {
        Promotion map = modelMapper.map(promotionRequestDto, Promotion.class);
        Promotion save = promotionRepository.save(map);
        return new PromoOffer(save.getPromoCode(), save.getDescription(),map.getValidFrom());
    }

    @Override
    public PromotionRequestDto getAll(){
        List<Promotion> promo = promotionRepository.findAll();
        return modelMapper.map(promo, PromotionRequestDto.class);
    }

    @Override
    public PromoOffer getPromoCode(String promoCode) {
        if(promotionRepository.findByPromoCode(promoCode)==null){
            throw new PromoCodeNotExistException("Promo code does not exist");
        }
        else {
            Promotion promo = promotionRepository.findByPromoCode(promoCode);
            return new PromoOffer(promo.getPromoCode(), promo.getDescription(), promo.getValidFrom());
        }
    }

    @Override
    public void deleteByid(String id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public NotificationResponseDto sendEmailNotification(NotificationRequestDto request) {

        // Step 1: Fetch Payment Details via Feign Client
        PaymentResponse payment = paymentClient.getPaymentStatus(request.getTransactionId());

        // Step 2: Fetch User Details (for personalization)
        UserResponse user = userClient.getUserById(request.getUserId());

        // Step 3: Fill placeholders
        Map<String, String> placeholders = Map.of(
                "name", user.getName(),
                "amount", String.valueOf(payment.getAmount()),
                "transactionId", payment.getTransactionId(),
                "status", payment.getOrderStatus(),
                "email", user.getEmail()
        );

        // Step 4: Load Template Based on Payment Status
        String templateFile = payment.getOrderStatus().equalsIgnoreCase("PAID")
                ? "payment-success.txt"
                : "payment-failed.txt";

        String message;
        try {
            message = templateUtil.loadTemplate(templateFile, placeholders);
        } catch (IOException e) {
            throw new TemplateLoadException("Failed to load template: " + templateFile, e);
        }

        // Step 5: Build Request Message and Save Notification
        request.setMessage(message);
        request.setContent(NotificationContent.TRANSACTION_ALERT);
        request.setChannel(NotificationChannel.EMAIL);

        return sendNotificationByChannel(request, NotificationChannel.EMAIL);
    }

    @Override
    public NotificationResponseDto sendSmsNotification(NotificationRequestDto request) {
        return sendNotificationByChannel(request, NotificationChannel.SMS);
    }

    @Override
    public NotificationResponseDto sendInAppNotification(NotificationRequestDto request) {
        return sendNotificationByChannel(request, NotificationChannel.IN_APP);
    }

    @Override
    public NotificationResponseDto saveNotification(NotificationRequestDto request, NotificationStatus status) {
        Notifications notification = buildNotification(request);
        notification.setStatus(status);
        Notifications saved = notificationRepository.save(notification);
        return modelMapper.map(saved, NotificationResponseDto.class);
    }

    private NotificationResponseDto sendNotificationByChannel(NotificationRequestDto request,
                                                              NotificationChannel channel) {
        Notifications notification = buildNotification(request);
        notification.setChannel(channel);
        notification.setStatus(NotificationStatus.PENDING);

        Notifications saved = notificationRepository.save(notification);
        try {
            System.out.printf("Sending %s notification to user %s%n", channel, request.getUserId());
            System.out.println("Message: " + request.getMessage());
            saved.setStatus(NotificationStatus.SENT);
        } catch (Exception e) {
            saved.setStatus(NotificationStatus.FAILED);
            System.err.println("Error sending " + channel + " notification: " + e.getMessage());
        }

        Notifications updated = notificationRepository.save(saved);
        return modelMapper.map(updated, NotificationResponseDto.class);
    }

    private Notifications buildNotification(NotificationRequestDto request) {
        return modelMapper.map(request, Notifications.class);
    }
}
