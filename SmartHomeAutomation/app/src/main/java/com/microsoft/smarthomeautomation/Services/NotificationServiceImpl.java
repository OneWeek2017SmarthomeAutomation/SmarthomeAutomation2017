package com.microsoft.smarthomeautomation.Services;


import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by andlam on 7/24/2017.
 */

public class NotificationServiceImpl implements NotificationService {

    private Context context;

    public NotificationServiceImpl(Context context) {
        this.context = context;
    }

    /**
     *
     * @param title
     * @param subtitle
     * @return
     */
    @Override
    public void DisplayNotification(String title, String subtitle) {
        DisplayNotification(title, subtitle, null, null);
    }

    @Override
    public void DisplayNotification(String title, String subtitle, NotificationCompat.Action primaryCommand, NotificationCompat.Action alternateCommand) {
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(android.R.drawable.ic_notification_overlay)
                        .setContentTitle(title)
                        .setContentText(subtitle);

        if (primaryCommand != null) {
            notificationBuilder.addAction(primaryCommand);
        }
        if (alternateCommand != null) {
            notificationBuilder.addAction(alternateCommand);
        }

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);
        notificationManager.notify(12345, notificationBuilder.build());
    }
}
