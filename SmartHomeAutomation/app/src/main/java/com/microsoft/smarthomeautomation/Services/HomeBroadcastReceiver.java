
package com.microsoft.smarthomeautomation.Services;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.microsoft.smarthomeautomation.Constants;
import com.microsoft.smarthomeautomation.SmartHomeApplication;

/**
 * Handle all broadcast messages to the app. OnReceive runs on the UI thread.
 */
public class HomeBroadcastReceiver extends WakefulBroadcastReceiver {

    /**
     * A flag indicating if the instance is initialized.
     */
    private volatile boolean mInitialized;
    private SmartHomeApplication application;

    /*
     * (non-Javadoc)
     * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        // WakefulBroadcastReceiver is instantiated by the application automatically
        // and does not have method such as onCreate for us to inject the dependencies,
        // so we will inject the dependency the first time it's used. To be safe,
        // there is lock here but it shouldn't be too much of performance penalty.
        if (!mInitialized) {
            synchronized (this) {
                if (!mInitialized) {
                    // we need to initialize
                    application = (SmartHomeApplication) context.getApplicationContext();

                    mInitialized = true;
                }
            }
        }

        String action = intent.getAction();

        if (action.equalsIgnoreCase(Constants.INTENT_START_MEDIA)) {
            application.mediaService.StartMediaPlayer("filename");
        } else if (action.equalsIgnoreCase(Constants.INTENT_STOP_MEDIA)) {
            application.mediaService.StopMediaPlayer();
        } else if (action.equalsIgnoreCase(Constants.INTENT_SET_VOLUME)) {
            int volume = intent.getIntExtra(Constants.EXTRA_VOLUME, 3);
            application.mediaService.SetMediaVolume(volume);
        } else if (action.equalsIgnoreCase(Constants.INTENT_LAUNCH_NOTIFICATION)) {
            String Title = intent.getStringExtra(Constants.EXTRA_ALARM);
            String Subtitle = intent.getStringExtra(Constants.EXTRA_ALARM_SUBTITLE);
            application.notificationService.DisplayNotification(Title, Subtitle);
        } else if (action.equalsIgnoreCase(Constants.INTENT_NOTIFICATION_DISMISSED)) {
            application.alarmService.cancelAlarms();
            // TODO figure out the way to do this
        }
    }
}
