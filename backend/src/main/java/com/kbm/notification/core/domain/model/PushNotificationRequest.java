package com.kbm.notification.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PushNotificationRequest {

    private String title;
    private String message;
    private String topic;
    private String token;
}
