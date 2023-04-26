package com.sorawingwind.jechat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.sorawingwind.jechat.alarm.AlarmConfig;
import com.sorawingwind.jechat.notification.NotificationConfig;
import com.sorawingwind.jechat.receiver.DBCatchReceiver;

public class MessageService extends Service {

    private static final String ALARM_ACTION = "SAVE_WECHAT_DATA_ACTION";
    private static final int TIME_INTERVAL = 5000; // 5 sec 1000 * 5
    private static final String CHANNEL_ID = "1";
    private static final String CHANNEL_NAMW = "Message_Cnannel";
    private NotificationConfig notificationConfig;
    private  AlarmConfig alarmConfig;

    private DBCatchReceiver dbCatchReceiver;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    private static final String TAG = "MessageService";
    final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        MessageService getService() {
            return MessageService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "~~~ onbind ~~~");
        return mBinder;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "~~~ onCreate ~~~");
        //this.startFrontService();
        this.notificationConfig = new NotificationConfig();
        this.alarmConfig = new AlarmConfig(this);
        this.alarmConfig.createAlarm();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "~~~ onDestory ~~~");
//        stopForeground(true);
//        unregisterReceiver(dbCatchReceiver);
//        alarmManager.cancel(pendingIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "~~~ onStartCommand ~~~");
        this.startFrontService();
        this.dbCatchReceiver = new DBCatchReceiver(alarmConfig);
//        new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    Log.i(TAG, "~~ thread ~~");
//                    try {
//                        Thread.sleep(60000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    private void startFrontService() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(notificationConfig.createNotification(CHANNEL_ID, CHANNEL_NAMW));
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext(), CHANNEL_ID); //获取一个Notification构造器
        Intent nfIntent = new Intent(this, MainActivity.class);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
                .setContentTitle("Jechat") // 设置下拉列表里的标题
                .setContentText("持续获取微信数据") // 设置上下文内容
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间
        Notification notification = builder.build(); // 获取构建好的Notification
        startForeground(110, notification);
    }
}
