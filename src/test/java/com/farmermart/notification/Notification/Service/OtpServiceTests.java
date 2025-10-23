package com.farmermart.notification.Notification.Service;

//package com.farmermart.notification.service;

import com.farmermart.notification.Notification.Service.Services.OtpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OtpServiceTests {

    @Autowired
    private OtpService otpService;

    @BeforeEach
    void clearOtpStorage() throws Exception {
        // Reset in-memory OTP map before each test using reflection
        Field field = OtpService.class.getDeclaredField("otpStorage");
        field.setAccessible(true);
        Map<String, ?> map = (Map<String, ?>) field.get(null);
        map.clear();
    }

    // ✅ 1. Generate and verify OTP (Email)
    @Test
    void testGenerateAndVerifyEmailOtp() {
        String email = "user@example.com";
        String otp = otpService.generateOtp(email);

        assertNotNull(otp);
        assertEquals(6, otp.length());
        assertTrue(otpService.verifyOtp(email, otp), "OTP should be verified successfully");
    }

    // ✅ 2. Generate and verify OTP (Phone)
    @Test
    void testGenerateAndVerifyPhoneOtp() {
        String phone = "9876543210";
        String otp = otpService.generateOtp(phone);

        assertNotNull(otp);
        assertTrue(otpService.verifyOtp(phone, otp), "Phone OTP should verify correctly");
    }

    // ❌ 3. Verify with wrong OTP
    @Test
    void testVerifyWrongOtpFails() {
        String email = "test@example.com";
        otpService.generateOtp(email);

        assertFalse(otpService.verifyOtp(email, "000000"), "Verification should fail for wrong OTP");
    }

    // ⏰ 4. Expired OTP should fail
    @Test
    void testExpiredOtpFails() throws Exception {
        String key = "expired@example.com";
        String otp = otpService.generateOtp(key);

        // manually set expiry time to past
        Field field = OtpService.class.getDeclaredField("otpStorage");
        field.setAccessible(true);
        Map<String, Object> map = (Map<String, Object>) field.get(null);

        Object details = map.get(key.toLowerCase());
        Field expiresAtField = details.getClass().getDeclaredField("expiresAt");
        expiresAtField.setAccessible(true);
        expiresAtField.set(details, LocalDateTime.now().minusMinutes(10));

        assertFalse(otpService.verifyOtp(key, otp), "Expired OTP should not verify");
    }

    // ♻️ 5. OTP cannot be reused
    @Test
    void testOtpCannotBeReused() {
        String email = "reuse@test.com";
        String otp = otpService.generateOtp(email);

        assertTrue(otpService.verifyOtp(email, otp), "First attempt should succeed");
        assertFalse(otpService.verifyOtp(email, otp), "Second attempt should fail (OTP already used)");
    }

    // 🧹 6. Missing or invalid user key
    @Test
    void testMissingKeyOrOtp() {
        assertFalse(otpService.verifyOtp("", "123456"), "Empty key should fail");
        assertFalse(otpService.verifyOtp("9876543210", null), "Null OTP should fail");
    }

    // 🔄 7. New OTP replaces old one
    @Test
    void testNewOtpReplacesOld() {
        String phone = "9998887777";
        String firstOtp = otpService.generateOtp(phone);
        String secondOtp = otpService.generateOtp(phone);

        assertNotEquals(firstOtp, secondOtp, "New OTP should be different");
        assertFalse(otpService.verifyOtp(phone, firstOtp), "Old OTP should no longer be valid");
        assertTrue(otpService.verifyOtp(phone, secondOtp), "New OTP should verify correctly");
    }
}
