package com.kbm.notification.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PushNotificationResponse {
    private int status;
    private String message;
}
