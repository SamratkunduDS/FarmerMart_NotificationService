package com.farmermart.notification.Notification.Service.client;

import com.farmermart.notification.Notification.Service.dto.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentClient {

    @GetMapping("/payment/{id}")
    public PaymentResponse getPaymentById(@PathVariable("id") String id);
}
