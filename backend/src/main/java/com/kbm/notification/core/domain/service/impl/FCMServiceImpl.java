package com.kbm.notification.core.domain.service.impl;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import com.kbm.notification.core.domain.model.PushNotificationRequest;
import com.kbm.notification.core.domain.model.SubscriptionRequest;
import com.kbm.notification.core.domain.service.FCMService;
import com.kbm.notification.core.domain.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@RequiredArgsConstructor
public class FCMServiceImpl implements FCMService {

  private final ProcessLowRequestService processLowRequestService;
    @Override
    public void sendMessageToToken(PushNotificationRequest request) throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageToToken(request);
        String response = sendAndGetResponse(message);
    log.info(
        "Sent message to token. Device token: "
            + request.getToken()
            + ", "
            + response
            + " msg "
            + JsonUtil.toJson(message));
    }
    @Override
    public void sendNotificationToTarget(PushNotificationRequest request){
        try {
            final int adding = processLowRequestService.adding(5, 15);
            PushNotificationRequest newRequest = new PushNotificationRequest();
            newRequest.setTitle(request.getTitle());
            newRequest.setToken(request.getToken());
            Map<String, Object> body = JsonUtil.fromJson(request.getMessage(), Map.class);
            body.entrySet().stream().forEach(e-> System.out.println(" key: "+ e.getKey() + " value: "+  e.getValue()));
            newRequest.setMessage(String.format("El resultado encontrado ha sido %s", adding));
            Message message = getDirectMessage(newRequest);
            FirebaseMessaging.getInstance().sendAsync(message);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void sendNotificationToTopic(PushNotificationRequest request){
        Message message = getTopicMessage(request);
        FirebaseMessaging.getInstance().sendAsync(message);
    }

    @Override
    public void subscribeToTopic(SubscriptionRequest subscriptionRequest) throws FirebaseMessagingException {
    FirebaseMessaging.getInstance()
        .subscribeToTopic(Arrays.asList(subscriptionRequest.getSubscriber()), subscriptionRequest.getTopic());
    }

    private Message getDirectMessage(PushNotificationRequest request) {
        return Message.builder().setWebpushConfig(WebpushConfig.builder().setNotification(WebpushNotification.builder()
                                .setTitle(request.getTitle())
                                .setBody(request.getMessage())
                                .setIcon("https://assets.mapquestapi.com/icon/v2/incident@2x.png")
                                .build())
                        .build())
                .setToken(request.getToken()).build();
    }

    private Message getTopicMessage(PushNotificationRequest request) {
        return Message.builder().setWebpushConfig(WebpushConfig.builder().setNotification(WebpushNotification.builder()
                                .setTitle(request.getTitle())
                                .setBody(request.getMessage())
                                .setIcon("https://assets.mapquestapi.com/icon/v2/incident@2x.png")
                                .build())
                        .build())
                .setTopic(request.getTopic()).build();
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }


    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                        .setTag(topic).build()).build();
    }
    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }
    private Message getPreconfiguredMessageToToken(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
                .build();
    }
    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
                        Notification.builder().setTitle(request.getTitle()).setBody(request.getMessage()).build());
    }

}
