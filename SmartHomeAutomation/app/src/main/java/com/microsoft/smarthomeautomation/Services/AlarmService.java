package com.microsoft.smarthomeautomation.Services;

import android.os.Bundle;

import org.joda.time.DateTime;

public interface AlarmService {
    void setAlarm(DateTime startTime, String action, Bundle extras);
    void cancelAlarm(String intentKey);
    void cancelAlarms();
}
