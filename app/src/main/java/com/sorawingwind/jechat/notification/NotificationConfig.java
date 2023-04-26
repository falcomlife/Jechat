package com.sorawingwind.jechat.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;

public class NotificationConfig {

    public NotificationChannel  createNotification(String channelId,String channelName){
        NotificationChannel notificationChannel = new NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setShowBadge(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        return notificationChannel;
    }
}
