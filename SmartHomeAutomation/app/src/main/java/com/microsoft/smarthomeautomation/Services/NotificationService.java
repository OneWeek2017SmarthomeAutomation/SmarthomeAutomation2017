package com.microsoft.smarthomeautomation.Services;

import android.app.Notification;

public interface NotificationService {

    String DisplayNotification(String title, String subtitle);

    String DisplayNotification(String title, String subtitle, String primaryString, Notification.Action primaryCommand, String alternateString, Notification.Action alternateCommand);

}
