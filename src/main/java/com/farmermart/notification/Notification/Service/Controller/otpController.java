package com.farmermart.notification.Notification.Service.Controller;

import com.farmermart.notification.Notification.Service.Services.OtpService;
import com.farmermart.notification.Notification.Service.dto.request.OtpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/otp")
public class otpController {

    private final OtpService otpService;

    public otpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateOtp(@RequestBody OtpRequest request) {
        String otp;

        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            otp = otpService.generateOtp(request.getEmail());
            return ResponseEntity.ok("OTP sent to email: " + request.getEmail());
        } else if (request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty()) {
            otp = otpService.generateOtp(request.getPhoneNumber());
            return ResponseEntity.ok("OTP sent to phone: " + request.getPhoneNumber());
        } else {
            return ResponseEntity.badRequest().body("Email or phone number is required");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request) {

        // All possible field names
        String key = request.get("emailOrPhone");
        if (key == null) key = request.get("email");
        if (key == null) key = request.get("phoneNumber");

        System.out.println("✅ Extracted key: " + key);

        System.out.println(key);

        String otp = request.get("otp");             // must match JSON key

        if (key == null || otp == null) {
            return ResponseEntity.badRequest().body("Missing email or OTP in request");
        }

        boolean valid = OtpService.verifyOtp(key, otp);

        if (valid)
            return ResponseEntity.ok("OTP verified successfully!");
        else
            return ResponseEntity.badRequest().body("Invalid or expired OTP");
    }
}
