package com.example.push;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Push extends Application {

    public static final String Channel_1_ID = "Channel1";
    public static final String Channel_2_ID = "Channel2";


    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationsChannel();
    }

    private void createNotificationsChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    Channel_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH

            );
            channel1.setDescription("This is Channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    Channel_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW

            );
            channel2.setDescription("This is Channel 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);

        }
    }
}
