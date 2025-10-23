//package com.farmermart.notification.Notification.Service.Services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//
//@Service
//public class OtpService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    private final Map<String, OtpDetails> otpStorage = new HashMap<>();
//    private static final int OTP_LENGTH = 6;
//    private static final int EXPIRY_MINUTES = 5;
//
//    private record OtpDetails(String otp, LocalDateTime expiresAt) {}
//
//    public String generateOtp(String email) {
//        String otp = String.format("%06d", new Random().nextInt(999999));
//        LocalDateTime expiry = LocalDateTime.now().plusMinutes(EXPIRY_MINUTES);
//        otpStorage.put(email, new OtpDetails(otp, expiry));
//
//        // Send OTP email
//        sendOtpEmail(email, otp);
//
//        return otp;
//    }
//
//    public boolean verifyOtp(String email, String otp) {
//        OtpDetails details = otpStorage.get(email);
//        if (details == null) return false;
//        if (LocalDateTime.now().isAfter(details.expiresAt())) return false;
//        if (!details.otp().equals(otp)) return false;
//
//        otpStorage.remove(email); // OTP is one-time use
//        return true;
//    }
//
//    private void sendOtpEmail(String toEmail, String otp) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("sushant051296@gmail.com");
//        message.setTo(toEmail);
//        message.setSubject("Your OTP Code");
//        message.setText("""
//                Dear User,
//
//                Your OTP code is: %s
//
//                It will expire in 5 minutes.
//
//                Regards,
//                FarmerMart Notification Service
//                """.formatted(otp));
//
//        mailSender.send(message);
//        System.out.println("OTP sent to email: " + toEmail);
//    }
//}


/// /Twilio API with $15 left to spend on the website

//
//package com.farmermart.notification.Notification.Service.Services;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//
//@Service
//public class OtpService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    // ✅ Store OTPs mapped to either email or phone number
//    private final Map<String, OtpDetails> otpStorage = new HashMap<>();
//    private static final int OTP_LENGTH = 6;
//    private static final int EXPIRY_MINUTES = 5;
//
//    private record OtpDetails(String otp, LocalDateTime expiresAt) {}
//
//    // ✅ Twilio credentials (replace with your own)
//    private static final String ACCOUNT_SID = "AC50d0c0f0edda3206eccb6e652b3b1ae8";
//    private static final String AUTH_TOKEN = "028d43f42f7b55363ed3a54155ea76a0";
//    private static final String FROM_PHONE = "+12342991007"; // your Twilio number
//
//
////    twilio.account.sid=USa95ad48163fa18adb24202c088e4bc70
////    twilio.auth.token=028d43f42f7b55363ed3a54155ea76a0
////    twilio.phone.number=+12342991007
//
//    static {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//    }
//
//    // ✅ Common method to generate OTP
//    private String generateOtpCode() {
//        return String.format("%06d", new Random().nextInt(999999));
//    }
//
//    // ✅ Generate and send OTP (for either email or phone)
//    public String generateOtp(String emailOrPhone) {
//        String otp = generateOtpCode();
//        LocalDateTime expiry = LocalDateTime.now().plusMinutes(EXPIRY_MINUTES);
//        otpStorage.put(emailOrPhone, new OtpDetails(otp, expiry));
//
//        // Decide the sending mode
//        if (emailOrPhone.contains("@")) {
//            sendOtpEmail(emailOrPhone, otp);
//        } else {
//            sendOtpSms(emailOrPhone, otp);
//        }
//
//        return otp;
//    }
//
//    // ✅ Verify OTP for both email or phone
//    public boolean verifyOtp(String emailOrPhone, String otp) {
//        OtpDetails details = otpStorage.get(emailOrPhone);
//        if (details == null) return false;
//        if (LocalDateTime.now().isAfter(details.expiresAt())) return false;
//        if (!details.otp().equals(otp)) return false;
//
//        otpStorage.remove(emailOrPhone); // one-time use
//        return true;
//    }
//
//    // ✅ Email sending logic (same as before)
//    private void sendOtpEmail(String toEmail, String otp) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("sushant051296@gmail.com");
//        message.setTo(toEmail);
//        message.setSubject("Your OTP Code");
//        message.setText("""
//                Dear User,
//
//                Your OTP code is: %s
//
//                It will expire in 5 minutes.
//
//                Regards,
//                FarmerMart Notification Service
//                """.formatted(otp));
//
//        mailSender.send(message);
//        System.out.println("✅ OTP sent to email: " + toEmail);
//    }
//
//    // ✅ SMS sending logic using Twilio
//    private void sendOtpSms(String phoneNumber, String otp) {
//        try {
//            Message.creator(
//                    new PhoneNumber(phoneNumber),
//                    new PhoneNumber(FROM_PHONE),
//                    "Your FarmerMart OTP is: " + otp + ". It will expire in 5 minutes."
//            ).create();
//            System.out.println("OTP sent to phone: " + phoneNumber);
//        } catch (Exception e) {
//            System.err.println("Failed to send SMS OTP: " + e.getMessage());
//        }
//
//
//    }
//}



///Fast2SMS website integration

package com.farmermart.notification.Notification.Service.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;


    @Value("${fast2sms.api.key}")
    private String fast2SmsApiKey;

    @Value("${fast2sms.sender.id}")
    private String senderId;

    private static final Map<String, OtpDetails> otpStorage = new HashMap<>();
    private static final int OTP_LENGTH = 6;
    private static final int EXPIRY_MINUTES = 5;

    private record OtpDetails(String otp, LocalDateTime expiresAt) {}

    // ✅ Generate random 6-digit OTP
    private String generateOtpCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    //Common normalization method
    private static String normalizeKey(String emailOrPhone){
        if(emailOrPhone.contains("@")){
            return emailOrPhone.trim().toLowerCase();
        }else{
            return emailOrPhone.replaceAll("[^0-9]","");
        }
    }

    // ✅ Generate and send OTP (email or SMS)
    public String generateOtp(String emailOrPhone) {
        String key = normalizeKey(emailOrPhone);
        String otp = generateOtpCode();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(EXPIRY_MINUTES);
        otpStorage.put(key, new OtpDetails(otp, expiry));

        //Checking
        System.out.println("🔎 Looking for OTP of: " + emailOrPhone);
        System.out.println("📦 Current OTP storage: " + otpStorage);
        System.out.println(key);



        if (key.contains("@")) {
            sendOtpEmail(emailOrPhone, otp);
        } else {
            sendOtpSms(key, otp);
        }

        return otp;
    }

    // ✅ Verify OTP
    public static boolean verifyOtp(String emailOrPhone, String otp) {
        String key = normalizeKey(emailOrPhone);
        System.out.println("Verifying OTP for key: " + key + " | map: " + otpStorage);
        OtpDetails details = otpStorage.get(key);
        System.out.println(details);
        System.out.println(key);
        if (details == null) {
            System.out.println("No OTP found for the key" + key);
            return false;
        }
        if (LocalDateTime.now().isAfter(details.expiresAt())) {
            System.out.println("OTP expired for key" + key);
            otpStorage.remove(key);
            return false;
        }
        if (!details.otp().equals(otp)){
            System.out.println("Invalid OTP for the key" + key);
            return false;
        }

        otpStorage.remove(key);
        System.out.println("OTP successfully verified");
        return true;
    }

    // ✅ Send OTP via Email
    private void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sushant051296@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Your OTP Code");
        message.setText("""
                Dear User,
                
                Your OTP code is: %s
                
                It will expire in 5 minutes.
                
                Regards,
                FarmerMart Notification Service
                """.formatted(otp));

        mailSender.send(message);
        System.out.println("✅ OTP sent to email: " + toEmail);
    }

    // ✅ Send OTP via Fast2SMS
    private void sendOtpSms(String phoneNumber, String otp) {
        try {
            String message = "Your FarmerMart OTP is: " + otp + ". It will expire in 5 minutes.";

            String url = "https://www.fast2sms.com/dev/bulkV2";

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("authorization", fast2SmsApiKey);

//            // Request body as JSON
//            String body = """
//                    {
//                        "sender_id": "%s",
//                        "message": "%s",
//                        "route": "q",
//                        "numbers": "%s"
//                    }
//                    """.formatted(senderId, message, phoneNumber);

            // Build JSON body using Map
            Map<String, Object> body = new HashMap<>();
            body.put("sender_id", senderId); // Must be verified for OTP
            body.put("message", message);
            body.put("route", "q"); // OTP route
            body.put("numbers", phoneNumber);
            body.put("flash", 0);

            //use this as the template for the json body to send in postman
            //{
            //  "route": "q",
            //  "message": "Your OTP is 1234",
            //  "phoneNumber": "8861001580",
            //  "flash": 0,
            //  "sender_id": "FARMRT"
            //}


            HttpEntity<Map<String,Object>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("✅ OTP sent to phone: " + phoneNumber);
            } else {
                System.err.println("❌ Failed to send SMS OTP: " + response.getBody());
            }

        } catch (Exception e) {
            System.err.println("❌ Error sending SMS OTP: " + e.getMessage());
        }
    }
}

