package com.microsoft.smarthomeautomation;

import android.app.Application;

import com.microsoft.smarthomeautomation.Services.AlarmService;
import com.microsoft.smarthomeautomation.Services.AlarmServiceImpl;
import com.microsoft.smarthomeautomation.Services.MediaService;
import com.microsoft.smarthomeautomation.Services.MediaServiceImpl;
import com.microsoft.smarthomeautomation.Services.NetworkService;
import com.microsoft.smarthomeautomation.Services.NetworkServiceImpl;
import com.microsoft.smarthomeautomation.Services.NotificationService;
import com.microsoft.smarthomeautomation.Services.NotificationServiceImpl;
import com.microsoft.smarthomeautomation.Services.SettingsProvider;

import net.danlew.android.joda.JodaTimeAndroid;

public class SmartHomeApplication extends Application {

    public AlarmService alarmService;
    public MediaService mediaService;
    public NetworkService networkService;
    public NotificationService notificationService;

    private static SmartHomeApplication instance;
    public SettingsProvider settingsProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        this.alarmService = new AlarmServiceImpl(this);
        this.mediaService = new MediaServiceImpl(this);
        this.networkService = new NetworkServiceImpl(this);
        this.notificationService = new NotificationServiceImpl(this);
        this.settingsProvider = new SettingsProvider(this);

        instance = this;
    }

    public static SmartHomeApplication getInstance() {
        return instance;
    }
}
