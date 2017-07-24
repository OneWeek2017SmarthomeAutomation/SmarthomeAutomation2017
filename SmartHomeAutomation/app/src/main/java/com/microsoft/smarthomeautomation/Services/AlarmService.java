package com.microsoft.smarthomeautomation.Services;

import org.joda.time.DateTime;

public interface AlarmService {
    public void setAlarm(DateTime startTime, int tag);
    public void cancelAlarm(int tag);
}
