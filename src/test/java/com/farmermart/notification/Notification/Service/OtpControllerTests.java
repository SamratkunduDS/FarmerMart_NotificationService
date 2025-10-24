package com.farmermart.notification.Notification.Service;

//package com.farmermart.notification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.farmermart.notification.Notification.Service.Services.OtpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OtpControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OtpService otpService;

    @Autowired
    private ObjectMapper objectMapper;

    private String testEmail;
    private String testPhone;

    @BeforeEach
    void setup() {
        testEmail = "test@example.com";
        testPhone = "9876543210";
    }

    // 1. Send OTP to email
    @Test
    void testSendOtpToEmail() throws Exception {
        Map<String, String> body = new HashMap<>();
        body.put("emailOrPhone", testEmail);

        mockMvc.perform(post("/otp/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("OTP sent successfully")));
    }

    // 2. Send OTP to phone
    @Test
    void testSendOtpToPhone() throws Exception {
        Map<String, String> body = new HashMap<>();
        body.put("emailOrPhone", testPhone);

        mockMvc.perform(post("/otp/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("OTP sent successfully")));
    }

    // 3. Send OTP with missing field
    @Test
    void testSendOtpMissingField() throws Exception {
        Map<String, String> body = new HashMap<>();
        // no emailOrPhone key

        mockMvc.perform(post("/otp/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Missing email or phone")));
    }

    // 4. Verify valid OTP (happy path)
    @Test
    void testVerifyValidOtp() throws Exception {
        // Generate OTP directly
        String otp = otpService.generateOtp(testEmail);

        Map<String, String> verifyBody = new HashMap<>();
        verifyBody.put("emailOrPhone", testEmail);
        verifyBody.put("otp", otp);

        mockMvc.perform(post("/otp/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(verifyBody)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("OTP verified successfully")));
    }

    // 5. Verify with wrong OTP
    @Test
    void testVerifyInvalidOtp() throws Exception {
        otpService.generateOtp(testPhone);

        Map<String, String> verifyBody = new HashMap<>();
        verifyBody.put("emailOrPhone", testPhone);
        verifyBody.put("otp", "000000");

        mockMvc.perform(post("/otp/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(verifyBody)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Invalid or expired OTP")));
    }

    // 6. Verify missing fields
    @Test
    void testVerifyMissingFields() throws Exception {
        Map<String, String> body = new HashMap<>();
        body.put("emailOrPhone", testEmail);
        // missing otp

        mockMvc.perform(post("/otp/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Missing email or OTP")));
    }
}
