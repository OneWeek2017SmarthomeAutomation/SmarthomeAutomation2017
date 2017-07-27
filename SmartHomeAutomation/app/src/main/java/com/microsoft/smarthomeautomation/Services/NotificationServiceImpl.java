package com.microsoft.smarthomeautomation.Services;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.microsoft.smarthomeautomation.Constants;

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
        Intent receiver = new Intent(context, HomeBroadcastReceiver.class);
        receiver.setAction(Constants.INTENT_STOP_MEDIA);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, receiver, 0);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(android.R.drawable.ic_notification_overlay)
                        .setContentTitle(title)
                        .setContentIntent(pendingIntent)
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
