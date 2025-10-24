package com.farmermart.notification.Notification.Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
<<<<<<< HEAD
@EnableFeignClients(basePackages = "com.farmermart.notification.Notification.Service.client")
public class NotificationServiceApplication {
=======
public class 	NotificationServiceApplication {
>>>>>>> origin/sushant-1.0

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
