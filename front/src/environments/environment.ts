// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

const HOST = 'http://localhost:9053';
const BASE = '/api/v1/notification';

export const environment = {
  production: false,
  firebaseConfig :  {
    apiKey: "XXXXXXXXXXXXXXXXXXXXXX",
    authDomain: "XXXXXXXXXXXXXXXXXXXXXX",
    projectId: "XXXXXXXXXXXXXXXXXXXXXX",
    storageBucket: "XXXXXXXXXXXXXXXXXXXXXX",
    messagingSenderId: "XXXXXXXXXXXXXXXXXXXXXX",
    appId: "XXXXXXXXXXXXXXXXXXXXXX",
    vapidKey: "XXXXXXXXXXXXXXXXXXXXXX"
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
