package com.microsoft.smarthomeautomation.Services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.joda.time.DateTime;

import static android.content.ContentValues.TAG;

public class AlarmServiceImpl implements AlarmService {

    private Context context;

    public AlarmServiceImpl(Context context){
        this.context = context;
    }

    @Override
    public void setAlarm(DateTime startTime, String action, Bundle bundle) {
        Intent intent = new Intent(context, HomeBroadcastReceiver.class);
        intent.setAction(action);
        intent.putExtras(bundle);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Log.d("AlarmServiceImpl", "Scheduling alarm:" + action + " startTime " + startTime);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, startTime.getMillis(), pendingIntent);
    }

    @Override
    public void cancelAlarm(String intentKey) {
        Intent receiver = new Intent(context, HomeBroadcastReceiver.class);
        receiver.setAction(intentKey);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, receiver, 0);

        alarmManager.cancel(pendingIntent);
    }

    @Override
    public void cancelAlarms() {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent updateIntent = new Intent(context, HomeBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, updateIntent, 0);

        try {
            alarmManager.cancel(pendingIntent);
        } catch (Exception e) {
            Log.e(TAG, "AlarmManager update was not canceled. " + e.toString());
        }

    }
}
