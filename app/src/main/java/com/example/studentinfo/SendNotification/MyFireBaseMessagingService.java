package com.example.studentinfo.SendNotification;

import android.app.NotificationManager;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.studentinfo.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    String studentId, description;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        studentId = remoteMessage.getData().get("studentId");
        description = remoteMessage.getData().get("description");

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext()).setSmallIcon(R.drawable.ic_android_black_24dp).setContentTitle(studentId)
                .setContentText(description);
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
}
