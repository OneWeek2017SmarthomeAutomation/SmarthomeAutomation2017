package com.microsoft.smarthomeautomation.Services;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.microsoft.smarthomeautomation.DTO.Action;
import com.microsoft.smarthomeautomation.SmartHomeApplication;
import com.microsoft.smarthomeautomation.Utils;

import java.util.ArrayList;

public class NetworkServiceImpl implements NetworkService {

    private final SmartHomeApplication application;

    public NetworkServiceImpl(SmartHomeApplication application){
        this.application = application;
    }

    @Override
    public ArrayList<Action> FetchActions() {
        ArrayList<Action> actions = new ArrayList<>();

        // TODO: fetch actions

        if (actions.size() < 1) {
            return null;
        }

        this.updateActionsIfNecessary(actions);

        return actions;
    }

    public void SetSampleData(int sampleDataToUse) {

    }

    private void updateActionsIfNecessary(ArrayList<Action> actions) {
        if (application.settingsProvider.isLastDownloadedActionsDifferent(actions)) {
            application.settingsProvider.saveLastDownloadedActions(actions);
            Utils.parseActionsAndSetAlarms(application, actions);
        }
    }

    @Override
    public Void PutActions(ArrayList<Action> actions) {

        return null;
    }

    @Override
    public void setLightBrightness(int brightness) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(application);
        String url ="http://smarthome20170724050613.azurewebsites.net/operations/bulb/" + brightness;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
