package com.farmermart.notification.Notification.Service.client;

import com.farmermart.notification.Notification.Service.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @GetMapping("/user/{userid}")
    public UserResponse getUserById(@PathVariable("userid") String userid);
}
