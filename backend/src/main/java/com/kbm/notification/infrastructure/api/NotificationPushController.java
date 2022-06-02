package com.kbm.notification.infrastructure.api;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.kbm.notification.core.domain.model.PushNotificationRequest;
import com.kbm.notification.core.domain.model.PushNotificationResponse;
import com.kbm.notification.core.domain.model.SubscriptionRequest;
import com.kbm.notification.core.domain.service.FCMService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/api/v1/notification")
@Validated
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class NotificationPushController {

    private final FCMService fcmService;

    @PostMapping("/token")
    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) throws ExecutionException, InterruptedException {
        fcmService.sendMessageToToken(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity sendDirectNotification(@RequestBody PushNotificationRequest request) {
        fcmService.sendNotificationToTarget(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/topic")
    public ResponseEntity sendNotificationToTopic(@RequestBody PushNotificationRequest request) {
        fcmService.sendNotificationToTopic(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification to topic has been sent."), HttpStatus.OK);
    }

    @PostMapping("/topic/subscription")
    public ResponseEntity subscribeToTopic(@RequestBody SubscriptionRequest request) throws FirebaseMessagingException {
        fcmService.subscribeToTopic(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Subscription has been initialized."), HttpStatus.OK);
    }
}
