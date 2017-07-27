package com.microsoft.smarthomeautomation;

import android.os.Bundle;

import com.microsoft.smarthomeautomation.DTO.Action;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class Utils {

    public static void parseActionsAndSetAlarms(SmartHomeApplication application, ArrayList<Action> actions){
        for (Action action : actions) {
            if (action.Enabled) {
                Bundle bundle;
                switch(action.Type) {
                    case "WakeUpSong":
                        DateTime startTime = action.StartTime;
                        if (startTime.minusMinutes(5).isAfter(DateTime.now())) {
                            bundle = new Bundle();
                            bundle.putString(Constants.EXTRA_ALARM, "Time to wake up!");
                            bundle.putString(Constants.EXTRA_ALARM_SUBTITLE, "Tap to dismiss alarm.");
                            application.alarmService.setAlarm(action.StartTime.minusMinutes(5), Constants.INTENT_LAUNCH_NOTIFICATION, bundle);
                        }
                        if (startTime.isAfter(DateTime.now())) {
                            application.alarmService.setAlarm(action.StartTime, Constants.INTENT_START_MEDIA, new Bundle());
                        }

                        if (startTime.plusMinutes(1).isAfter(DateTime.now())) {
                            bundle = new Bundle();
                            bundle.putInt(Constants.EXTRA_VOLUME, 2);
                            application.alarmService.setAlarm(action.StartTime.plusMinutes(1), Constants.INTENT_SET_VOLUME, bundle);
                        }

                        if (startTime.plusMinutes(2).isAfter(DateTime.now())) {
                            bundle = new Bundle();
                            bundle.putInt(Constants.EXTRA_VOLUME, 3);
                            application.alarmService.setAlarm(action.StartTime.plusMinutes(2), Constants.INTENT_SET_VOLUME, bundle);
                        }

                        if (startTime.plusMinutes(3).isAfter(DateTime.now())) {
                            bundle = new Bundle();
                            bundle.putInt(Constants.EXTRA_VOLUME, 4);
                            application.alarmService.setAlarm(action.StartTime.plusMinutes(3), Constants.INTENT_SET_VOLUME, bundle);
                        }

                        if (startTime.plusMinutes(4).isAfter(DateTime.now())) {
                            bundle = new Bundle();
                            bundle.putInt(Constants.EXTRA_VOLUME, 5);
                            application.alarmService.setAlarm(action.StartTime.plusMinutes(4), Constants.INTENT_SET_VOLUME, bundle);
                        }

                        if (startTime.plusMinutes(5).isAfter(DateTime.now())) {
                            bundle = new Bundle();
                            bundle.putInt(Constants.EXTRA_VOLUME, 6);
                            application.alarmService.setAlarm(action.StartTime.plusMinutes(5), Constants.INTENT_SET_VOLUME, bundle);
                        }

                        break;
                    case "WakeUpLight":
                        application.alarmService.setAlarm(action.StartTime.plusMinutes(5), Constants.INTENT_ENABLE_LIGHT, new Bundle());
                        break;
                    case "TurnOffLight":
                        application.alarmService.setAlarm(action.StartTime.plusMinutes(5), Constants.INTENT_DISABLE_LIGHT, new Bundle());
                        break;
                    case "Commute":
                        bundle = new Bundle();
                        application.alarmService.setAlarm(action.StartTime, Constants.INTENT_LAUNCH_NOTIFICATION, bundle);
                        application.notificationService.DisplayNotification("Commute Ready!", "Your commute will be arriving at 9:24AM");
                    default:
                        break;
                }
            }
        }
    }
}
