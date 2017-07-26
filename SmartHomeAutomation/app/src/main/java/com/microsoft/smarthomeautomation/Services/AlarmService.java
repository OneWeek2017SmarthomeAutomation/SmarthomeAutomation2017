package com.microsoft.smarthomeautomation.Services;

import org.joda.time.DateTime;

public interface AlarmService {
    void setAlarm(DateTime startTime, int tag);
    void cancelAlarm(int tag);
    void cancelAlarms();
}
