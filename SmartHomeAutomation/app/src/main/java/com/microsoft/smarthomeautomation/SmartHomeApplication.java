package com.microsoft.smarthomeautomation;

import android.app.Application;

import com.microsoft.smarthomeautomation.Services.AlarmService;
import com.microsoft.smarthomeautomation.Services.AlarmServiceImpl;
import com.microsoft.smarthomeautomation.Services.MediaService;
import com.microsoft.smarthomeautomation.Services.NetworkService;
import com.microsoft.smarthomeautomation.Services.NetworkServiceImpl;
import com.microsoft.smarthomeautomation.Services.NotificationService;
import com.microsoft.smarthomeautomation.Services.NotificationServiceImpl;

public class SmartHomeApplication extends Application {

    public AlarmService alarmService;
    public MediaService mediaService;
    public NetworkService networkService;
    public NotificationService notificationService;

    private static SmartHomeApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.alarmService = new AlarmServiceImpl();
        this.mediaService = null;
        this.networkService = new NetworkServiceImpl(this);
        this.notificationService = new NotificationServiceImpl(this);

        instance = this;
    }

    public static SmartHomeApplication getInstance() {
        return instance;
    }
}
