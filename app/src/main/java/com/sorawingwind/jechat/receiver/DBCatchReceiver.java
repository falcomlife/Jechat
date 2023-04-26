package com.sorawingwind.jechat.receiver;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import com.sorawingwind.jechat.alarm.AlarmConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DBCatchReceiver extends BroadcastReceiver {

    private static final String TAG = "DBCatchProcess";
    private AlarmConfig alarmConfig;

    public DBCatchReceiver(AlarmConfig alarmConfig){
        this.alarmConfig = alarmConfig;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MessageService", "~~~ onReceive: save wechat data execute ~~~");
        //定时任务
        this.alarmConfig.createAlarm();
    }
}
