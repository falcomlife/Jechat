package com.sorawingwind.jechat.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sorawingwind.jechat.MessageService;

public class LaunchReceiver extends BroadcastReceiver {

    private static final String TAG = "LaunchReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"~~~ onReceive ~~~");
        context.startForegroundService(new Intent(context, MessageService.class));
    }
}
