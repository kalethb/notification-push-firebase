package com.kbm.notification.core.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequest {
    public String subscriber;
    public String topic;
}
