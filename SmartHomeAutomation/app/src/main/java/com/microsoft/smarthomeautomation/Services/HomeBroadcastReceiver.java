
package com.microsoft.smarthomeautomation.Services;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.microsoft.smarthomeautomation.Constants;
import com.microsoft.smarthomeautomation.DTO.Action;
import com.microsoft.smarthomeautomation.R;
import com.microsoft.smarthomeautomation.SmartHomeApplication;

import org.joda.time.DateTime;

import java.util.ArrayList;

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
        Log.d("HomeBroadcastReceiver", "Action = " + action);

        if (action.equalsIgnoreCase(Constants.INTENT_START_MEDIA)) {
            application.mediaService.StartMediaPlayer(R.raw.eye_of_the_tiger);
            application.notificationService.DisplayNotification("Good morning!", "Your first meeting is at 10:30 AM");
        } else if (action.equalsIgnoreCase(Constants.INTENT_STOP_MEDIA)) {
            application.mediaService.StopMediaPlayer();
            ArrayList<Action> actions = application.settingsProvider.getLastDownloadedActions();
            for (Action act : actions) {
                if (act.Type.equals("WakeUpSong")) {
                    act.Enabled = false;
                }
            }
            application.settingsProvider.saveLastDownloadedActions(actions);
        } else if (action.equalsIgnoreCase(Constants.INTENT_SET_VOLUME)) {
            int volume = intent.getIntExtra(Constants.EXTRA_VOLUME, 3);
            application.mediaService.SetMediaVolume(volume);
        } else if (action.equalsIgnoreCase(Constants.INTENT_FETCH_ACTIONS)) {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Action> results = application.networkService.FetchActions();
                    if (results != null) {
                        application.settingsProvider.saveLastDownloadedActions(results);
                    }
                }
            });

        } else if (action.equalsIgnoreCase(Constants.INTENT_LAUNCH_NOTIFICATION)) {
            String Title = intent.getStringExtra(Constants.EXTRA_ALARM);
            String Subtitle = intent.getStringExtra(Constants.EXTRA_ALARM_SUBTITLE);
            application.notificationService.DisplayNotification(Title, Subtitle);
        } else if (action.equalsIgnoreCase(Constants.INTENT_NOTIFICATION_DISMISSED)) {
            ArrayList<Action> actions = application.settingsProvider.getLastDownloadedActions();
            for (Action event : actions) {
                if (new DateTime(event.StartTime).isBefore(DateTime.now().plusMinutes(60))) {
                    event.Enabled = false;
                }
            }
            application.alarmService.cancelAlarms();
            application.mediaService.StopMediaPlayer();
            application.networkService.PutActions(actions);
        }
    }
}
