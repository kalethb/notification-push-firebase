import { Inject, Injectable } from '@angular/core';
import { getMessaging, getToken, onMessage } from "firebase/messaging";
import { map, tap } from 'rxjs/operators';

import { AngularFireMessaging } from '@angular/fire/compat/messaging';
import {HttpClient} from "@angular/common/http";
import { PushNotificationDto } from '../lib/models/push-notification-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NotificationPushService {

  constructor(private fireMessaging:  AngularFireMessaging, private http: HttpClient) {}

  message:any = null;
  messages: Array<PushNotificationDto> = [];


  requestPermission() {

    const messaging = getMessaging();
    const boyMessage = {
      name: "RIPS",
      valid: true,
      value: 100
    }

    getToken(messaging, { vapidKey: environment.firebaseConfig.vapidKey }).then((currentToken) => {
      if (currentToken) {
        console.log(currentToken)
         this.http.post(`${environment.SEND_NOTIFICATION_BY_TOKEN}`, {
             token: currentToken,
             message: JSON.stringify(boyMessage),
             title: "Notificacion push"
         }).pipe(map(e => {
          this.listen();
          return e;
        })) .subscribe(console.log)

      } else {
        // Show permission request UI
        console.log('No registration token available. Request permission to generate one.');
        // ...
      }
    }).catch((err) => {
      console.log('An error occurred while retrieving token. ', err, {...err});
      // ...
    });

  }

  directNotification(){
    this.fireMessaging.requestToken.subscribe(token => {

      if(token){
        console.log(token, "#### TOKEN");
        this.http.post(`${environment.SEND_NOTIFICATION_BY_TOKEN}`, {
          token: token,
          message: "Envio de notificacion inical por token",
          title: "Notificacion push"
      }).subscribe(console.log)

      this.http.post(`${environment.SUBSCRIBE_NOTIFICATION}`, {
        topic: "rips",
        subscriber: token
    }).pipe(map(e => {
      this.onMessage();
      this.listen()
      return e;
    })) .subscribe(console.log)


      }

    });
  }


  topicNotification(){
    this.fireMessaging.requestToken.subscribe(token => {

      if(token){
        console.log(token, "#### TOKEN");
        this.http.post(`${environment.SEND_NOTIFICATION_BY_TOPIC}`, {
          topic: "rips",
          message: "Envio de notificacion inical por topic",
          title: "Notificacion push"
      }).subscribe(console.log)

      this.http.post(`${environment.SUBSCRIBE_NOTIFICATION}`, {
        topic: "rips",
        subscriber: token
    }).subscribe(console.log)


      }

    });
  }


  listen() {
    const messaging = getMessaging();
     onMessage(messaging, (payload) => {
      console.log('Message received. ', payload);
      this.message=payload;
    });
  }

  onMessage(){
    this.fireMessaging.onMessage((payload) => {
      // Get the data about the notification
      let notification = payload.notification;
      console.log(notification, "Notification")
      // Create a Message object and add it to the array
      this.messages.push({title: notification.title, body: notification.body, iconUrl: notification.icon});
     });
  }
}
