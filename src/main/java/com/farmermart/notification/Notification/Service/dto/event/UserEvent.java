package com.farmermart.notification.Notification.Service.dto.event;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEvent {
    private String id;
    private String name;
    private String email;
    private String mobile;
    private String role;
    private String type;
}
