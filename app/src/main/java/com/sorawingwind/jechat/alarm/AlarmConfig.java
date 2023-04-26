package com.sorawingwind.jechat.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;

import com.sorawingwind.jechat.receiver.DBCatchReceiver;

public class AlarmConfig {

    private static final String ALARM_ACTION = "SAVE_WECHAT_DATA_ACTION";
    private static final int TIME_INTERVAL = 5000; // 5 sec 1000 * 5
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private DBCatchReceiver dbCatchReceiver;

    public AlarmConfig(Context context){
        alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        dbCatchReceiver = new DBCatchReceiver(this);
        IntentFilter intentFilter = new IntentFilter(ALARM_ACTION);
        context.registerReceiver(dbCatchReceiver, intentFilter);
        pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(ALARM_ACTION), 0);
    }

    public void createAlarm(){
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + TIME_INTERVAL, pendingIntent);
    }

}
