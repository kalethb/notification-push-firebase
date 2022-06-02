package com.kbm.notification.core.domain.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.kbm.notification.core.domain.model.PushNotificationRequest;
import com.kbm.notification.core.domain.model.SubscriptionRequest;

import java.util.concurrent.ExecutionException;

public interface FCMService {

    void sendMessageToToken(PushNotificationRequest request) throws InterruptedException, ExecutionException;
    void sendNotificationToTarget(PushNotificationRequest request);
    void sendNotificationToTopic(PushNotificationRequest request);
    void subscribeToTopic(SubscriptionRequest subscriptionRequest) throws FirebaseMessagingException;
}
