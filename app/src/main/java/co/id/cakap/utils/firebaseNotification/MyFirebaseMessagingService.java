/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.id.cakap.utils.firebaseNotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import co.id.cakap.R;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.splashScreen.SplashScreenActivity;
import co.id.cakap.utils.DateHelper;

/**
 * NOTE: There can only be one service in each app that receives FCM messages. If multiple
 * are declared in the Manifest then the first one will be chosen.
 *
 * In order to make this Java sample functional, you must remove the following from the Kotlin messaging
 * service in the AndroidManifest.xml:
 *
 * <intent-filter>
 *   <action android:name="com.google.firebase.MESSAGING_EVENT" />
 * </intent-filter>
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    private String mTitle = "";
    private String mBody = "";
    private String mUrl = "";
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages
        // are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data
        // messages are the type
        // traditionally used with GCM. Notification messages are only received here in
        // onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated
        // notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages
        // containing both notification
        // and data payloads are treated as notification messages. The Firebase console always
        // sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        handleDataMessage(remoteMessage);
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]


    /**
     * Handle data with object.
     */
    private void handleDataMessage(RemoteMessage remoteMessage){
//        mTitle = remoteMessage.getData().get(Constant.FIREBASE_NOTIFICATION_TITLE);
//        mBody = remoteMessage.getData().get(Constant.FIREBASE_NOTIFICATION_BODY);

        try {
            mUrl = remoteMessage.getData().get(Constant.FIREBASE_NOTIFICATION_URL);
            mTitle = remoteMessage.getNotification().getTitle();
            mBody = remoteMessage.getNotification().getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sendNotification(mTitle, mBody, mUrl);
    }

    // [START on_new_token]

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }
    // [END on_new_token]

    /**
     * Schedule async work using WorkManager.
     */
    private void scheduleJob() {
        // [START dispatch_job]
        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(MyWorker.class)
                .build();
        WorkManager.getInstance().beginWith(work).enqueue();
        // [END dispatch_job]
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String title, String messageBody, String url) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor sharedPrefEd = sharedPreferences.edit();
//        sharedPrefEd.putString(Constant.FIREBASE_NOTIFICATION_TITLE, title);
//        sharedPrefEd.putString(Constant.FIREBASE_NOTIFICATION_BODY, messageBody);
//        sharedPrefEd.putString(Constant.FIREBASE_NOTIFICATION_DATE, DateHelper.getTimeNow());
        sharedPrefEd.putInt(Constant.FIREBASE_NOTIFICATION_MOVE, 1);
//        sharedPrefEd.putString(Constant.FIREBASE_NOTIFICATION_URL, url);
        sharedPrefEd.apply();

        Intent intent = new Intent(this, SplashScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.cakap_logo)
                        .setLargeIcon(BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.cakap_logo))
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
//                        .setStyle(new NotificationCompat.BigPictureStyle()
//                                .bigPicture(BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.cakap_logo))
//                                .bigLargeIcon(null))
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .setBigContentTitle(title)
//                                .setSummaryText("TEST SUMMARY")
                                .bigText(messageBody))
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    title,
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
