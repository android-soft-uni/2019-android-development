package com.inveitix.templateandroidapp.data.remote.messaging;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.inveitix.templateandroidapp.R;
import com.inveitix.templateandroidapp.ui.activities.MainActivity;

public class MessagingService extends FirebaseMessagingService {

    public static final String TITLE = "title";
    public static final String MESSAGE_BODY = "text";
    public static final String WEB_URL = "url";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData() != null) {
            sendNotification(remoteMessage.getData().get(TITLE),
                    remoteMessage.getData().get(MESSAGE_BODY),
                    remoteMessage.getData().get(WEB_URL));
        }
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    private void sendNotification(String title, String messageBody, String webUrl) {

        Intent resultIntent = new Intent(this, MainActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(getApplicationContext(), 0, resultIntent,
                        PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }
}
