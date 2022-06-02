import { AngularFireModule, FIREBASE_OPTIONS } from '@angular/fire/compat';

import { AngularFireMessagingModule } from '@angular/fire/compat/messaging';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { NotificationPushModule } from './components/notification-push/notification-push.module';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from 'src/environments/environment';
import { initializeApp } from 'firebase/app';
import { provideFirebaseApp } from '@angular/fire/app';

initializeApp(environment.firebaseConfig);
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NotificationPushModule,
    HttpClientModule,
    ServiceWorkerModule.register('firebase-messaging-sw.js', { enabled: true })
  ],
  providers: [
    { provide: FIREBASE_OPTIONS, useValue: environment.firebaseConfig }
],
  bootstrap: [AppComponent]
})
export class AppModule { }
