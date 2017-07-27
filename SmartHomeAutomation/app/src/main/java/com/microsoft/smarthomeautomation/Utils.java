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
                        DateTime startTime = new DateTime(action.StartTime);
                        if (startTime.minusMinutes(1).isAfter(DateTime.now())) {
                            bundle = new Bundle();
                            bundle.putString(Constants.EXTRA_ALARM, "Time to wake up!");
                            bundle.putString(Constants.EXTRA_ALARM_SUBTITLE, "Tap to dismiss alarm.");
                            application.alarmService.setAlarm(startTime.minusMinutes(1), Constants.INTENT_LAUNCH_NOTIFICATION, bundle);
                        }
                        if (startTime.isAfter(DateTime.now())) {
                            bundle = new Bundle();
                            bundle.putInt(Constants.EXTRA_VOLUME, 1);
                            application.alarmService.setAlarm(startTime, Constants.INTENT_SET_VOLUME, bundle);
                            application.alarmService.setAlarm(startTime, Constants.INTENT_START_MEDIA, new Bundle());
                        }

                        if (startTime.plusMinutes(1).isAfter(DateTime.now())) {
                            bundle = new Bundle();
                            bundle.putInt(Constants.EXTRA_VOLUME, 2);
                            application.alarmService.setAlarm(startTime.plusMinutes(1), Constants.INTENT_SET_VOLUME, bundle);
                        }

                        if (startTime.plusMinutes(2).isAfter(DateTime.now())) {
                            bundle = new Bundle();
                            bundle.putInt(Constants.EXTRA_VOLUME, 3);
                            application.alarmService.setAlarm(startTime.plusMinutes(2), Constants.INTENT_SET_VOLUME, bundle);
                        }

                        if (startTime.plusMinutes(3).isAfter(DateTime.now())) {
                            bundle = new Bundle();
                            bundle.putInt(Constants.EXTRA_VOLUME, 4);
                            application.alarmService.setAlarm(startTime.plusMinutes(3), Constants.INTENT_SET_VOLUME, bundle);
                        }

                        if (startTime.plusMinutes(4).isAfter(DateTime.now())) {
                            bundle = new Bundle();
                            bundle.putInt(Constants.EXTRA_VOLUME, 5);
                            application.alarmService.setAlarm(startTime.plusMinutes(4), Constants.INTENT_SET_VOLUME, bundle);
                        }

                        if (startTime.plusMinutes(5).isAfter(DateTime.now())) {
                            bundle = new Bundle();
                            bundle.putInt(Constants.EXTRA_VOLUME, 6);
                            application.alarmService.setAlarm(startTime.plusMinutes(5), Constants.INTENT_SET_VOLUME, bundle);
                        }

                        break;
                    case "WakeUpLights":
                        application.alarmService.setAlarm(new DateTime(action.StartTime), Constants.INTENT_ENABLE_LIGHT, new Bundle());
                        break;
                    case "TurnOffLight":
                        application.alarmService.setAlarm(new DateTime(action.StartTime), Constants.INTENT_DISABLE_LIGHT, new Bundle());
                        break;
                    case "Commute":
                        bundle = new Bundle();
                        bundle.putString(Constants.EXTRA_ALARM, "Commute Ready!");
                        bundle.putString(Constants.EXTRA_ALARM_SUBTITLE, "Your commute will be arriving at 9:24AM");
                        application.alarmService.setAlarm(new DateTime(action.StartTime), Constants.INTENT_LAUNCH_NOTIFICATION, bundle);
                    default:
                        break;
                }
            }
        }
    }
}
