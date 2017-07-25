package com.microsoft.smarthomeautomation.Services;

import android.support.v4.app.NotificationCompat;
import android.content.Context;

public interface NotificationService {

    void DisplayNotification(String title, String subtitle);

    void DisplayNotification(String title, String subtitle, NotificationCompat.Action primaryCommand, NotificationCompat.Action alternateCommand);

}
