// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

const HOST = 'http://localhost:9053';
const BASE = '/api/v1/notification';

export const environment = {
  production: false,
  firebaseConfig :  {
    apiKey: "AIzaSyDHcMQ2xc8oO-5Nf87AfxioRY_Vy1uK90Y",
    authDomain: "notification-push-3973e.firebaseapp.com",
    projectId: "notification-push-3973e",
    storageBucket: "notification-push-3973e.appspot.com",
    messagingSenderId: "717966679749",
    appId: "1:717966679749:web:3a19daeee88360dc871aac",
    vapidKey: "BJ5o5iy62qwiFud1Ak29PbDUc-NqM1yuvizE5XUoksGXDyjNGmre3gVPKrJAGHby9jV4B791KVrGXSzdPqm2_dc"
  },
  SEND_NOTIFICATION: `${HOST}${BASE}/token`,
  SEND_NOTIFICATION_BY_TOKEN: `${HOST}${BASE}`,
  SEND_NOTIFICATION_BY_TOPIC: `${HOST}${BASE}/token`,
  SUBSCRIBE_NOTIFICATION: `${HOST}${BASE}/topic/subscription`

};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
