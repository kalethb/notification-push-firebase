import { Component, OnInit } from '@angular/core';

import { FIREBASE_OPTIONS } from '@angular/fire/compat';
import { NotificationPushService } from './services/notification-push.service';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers:[{provide: FIREBASE_OPTIONS, useValue: environment.firebaseConfig} ]
})
export class AppComponent implements OnInit{
  title = 'notificationPush';
  messageResponse?: any;

  constructor(private service: NotificationPushService){}

  ngOnInit(){
    this.service.listen();
    this.service.requestPermission()
    //this.service.directNotification();
    //this.service.topicNotification();


    //console.log(this.service.message)
    //this.messageResponse = this.service.message;
  }
}
