
package com.microsoft.smarthomeautomation.Services;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.microsoft.smarthomeautomation.DTO.Action;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingsProvider {
    private static final String APP_PREFERENCES_KEY = "AppPrefs";

    private static final String LAST_DOWNLOADED_DATA = "LastDownloadedData";

    private final Context mContext;

    public SettingsProvider(Context context) {
        mContext = context;
    }

    public void saveLastDownloadedActions(ArrayList<Action> lastDownloadedActions) {
        Gson gson = new Gson();
        setString(getAppPreferences(), LAST_DOWNLOADED_DATA, gson.toJson(lastDownloadedActions));
    }

    public boolean isLastDownloadedActionsDifferent(ArrayList<Action> actions) {
        final String lastDownloadedActions = getAppPreferences().getString(LAST_DOWNLOADED_DATA, null);
        String currentActions = new Gson().toJson(lastDownloadedActions);
        return currentActions.equals(lastDownloadedActions);
    }

    public ArrayList<Action> getLastDownloadedActions() {
        final String lastDownloadedActions = getAppPreferences().getString(LAST_DOWNLOADED_DATA, null);
        ArrayList<Action> actions = new ArrayList<>();

        Action[] apps = new Gson().fromJson(lastDownloadedActions, Action[].class);
        if (apps != null){
            actions.addAll(Arrays.asList(apps));
        } else {
            actions.addAll(getSampleData());
        }

        return actions;
    }

    private boolean setString(SharedPreferences prefs, String key, String value) {
        Editor prefsEditor = prefs.edit();
        prefsEditor.putString(key, value);
        return prefsEditor.commit();
    }

    private boolean setBoolean(SharedPreferences prefs, String key, boolean value) {
        Editor prefsEditor = prefs.edit();
        prefsEditor.putBoolean(key, value);
        return prefsEditor.commit();
    }

    private boolean setFloat(SharedPreferences prefs, String key, float value) {
        Editor prefsEditor = prefs.edit();
        prefsEditor.putFloat(key, value);
        return prefsEditor.commit();
    }

    private boolean setInt(SharedPreferences prefs, String key, int value) {
        Editor prefsEditor = prefs.edit();
        prefsEditor.putInt(key, value);
        return prefsEditor.commit();
    }

    private boolean setLong(SharedPreferences prefs, String key, long value) {
        Editor prefsEditor = prefs.edit();
        prefsEditor.putLong(key, value);
        return prefsEditor.commit();
    }

    private SharedPreferences getAppPreferences() {
        return getSharedPreferences(APP_PREFERENCES_KEY);
    }

    private SharedPreferences getSharedPreferences(String name) {
        return mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public ArrayList<Action> getSampleDataImmediately() {
        DateTime startTime = DateTime.now().plusMinutes(6);
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(new Action("Play Wakeup Song", "WakeUpSong", "", startTime, true, true));
        actions.add(new Action("Turn lights up", "WakeUpLights", "", startTime.plusMinutes(5), true, true));
        actions.add(new Action("Make Home Comfortable", "Home", "", startTime.plusMinutes(30), true, true));
        actions.add(new Action("Ordering Commute", "Commute", "", startTime.plusHours(1).plusMinutes(30), true, true));
        actions.add(new Action("Home Standby Sequence", "Home", "", startTime.plusHours(2).plusMinutes(30), true, true));
        actions.add(new Action("Ordering Commute Home", "Commute", "", startTime.plusHours(10).plusMinutes(30), true, true));
        actions.add(new Action("Make Home Comfortable", "Home", "", startTime.plusHours(11), true, true));
        actions.add(new Action("Home Standby Sequence", "Home", "", startTime.plusHours(15), true, true));
        return actions;
    }


    public ArrayList<Action> getSampleData() {
        DateTime startTime = new DateTime().withTimeAtStartOfDay().plusDays(1).plusHours(7);
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(new Action("Play Wakeup Song", "WakeUpSong", "", startTime, true, true));
        actions.add(new Action("Turn lights up", "WakeUpLights", "", startTime.plusMinutes(5), true, true));
        actions.add(new Action("Make Home Comfortable", "Home", "", startTime.plusMinutes(30), true, true));
        actions.add(new Action("Ordering Commute", "Commute", "", startTime.plusHours(1).plusMinutes(30), true, true));
        actions.add(new Action("Home Standby Sequence", "Home", "", startTime.plusHours(2).plusMinutes(30), true, true));
        actions.add(new Action("Ordering Commute Home", "Commute", "", startTime.plusHours(10).plusMinutes(30), true, true));
        actions.add(new Action("Make Home Comfortable", "Home", "", startTime.plusHours(11), true, true));
        actions.add(new Action("Home Standby Sequence", "Home", "", startTime.plusHours(15), true, true));
        return actions;
    }

    public ArrayList<Action> getSampleTrafficData() {
        DateTime startTime = new DateTime().withTimeAtStartOfDay().plusDays(1).plusHours(7).minusMinutes(18);
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(new Action("Play Wakeup Song", "WakeUpSong", "", startTime, true, true));
        actions.add(new Action("Turn lights up", "WakeUpLights", "", startTime.plusMinutes(5), true, true));
        actions.add(new Action("Make Home Comfortable", "Home", "", startTime.plusMinutes(30), true, true));
        actions.add(new Action("Ordering car early due to traffic", "Commute", "", startTime.plusHours(1).plusMinutes(30), true, true));
        actions.add(new Action("Home Standby Sequence", "Home", "", startTime.plusHours(2).plusMinutes(30), true, true));
        actions.add(new Action("Ordering Commute Home", "Commute", "", startTime.plusHours(10).plusMinutes(30).plusMinutes(18), true, true));
        actions.add(new Action("Make Home Comfortable", "Home", "", startTime.plusHours(11).plusMinutes(18), true, true));
        actions.add(new Action("Home Standby Sequence", "Home", "", startTime.plusHours(15).plusMinutes(18), true, true));
        return actions;
    }
}
