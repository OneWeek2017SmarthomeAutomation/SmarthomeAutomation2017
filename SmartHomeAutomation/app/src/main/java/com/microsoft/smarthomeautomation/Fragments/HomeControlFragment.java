package com.microsoft.smarthomeautomation.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.microsoft.smarthomeautomation.R;
import com.microsoft.smarthomeautomation.SmartHomeApplication;
import com.microsoft.smarthomeautomation.Utils;

public class HomeControlFragment extends Fragment {

    private SmartHomeApplication application;

    public HomeControlFragment() {
        // Required empty public constructor
    }

    public static HomeControlFragment newInstance(String param1, String param2) {
        HomeControlFragment fragment = new HomeControlFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.application = SmartHomeApplication.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_control, container, false);
        view.findViewById(R.id.lights_on).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.networkService.setLightBrightness(99);
            }
        });

        view.findViewById(R.id.lights_off).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.networkService.setLightBrightness(0);
            }
        });

        ((SeekBar)view.findViewById(R.id.lights_seekbar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public int progress;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                this.progress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                application.networkService.setLightBrightness(progress);
            }
        });

        view.findViewById(R.id.media_on).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.mediaService.StartMediaPlayer(R.raw.eye_of_the_tiger);
            }
        });

        view.findViewById(R.id.media_off).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.mediaService.StopMediaPlayer();
            }
        });

        view.findViewById(R.id.set_mode_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.settingsProvider.saveLastDownloadedActions(application.settingsProvider.getSampleData());
                Utils.parseActionsAndSetAlarms(application, application.settingsProvider.getLastDownloadedActions());
                application.notificationService.DisplayNotification("We will wake you up between 6:30 and 7:00 tomorrow", "If you don't plan to drive, go to the app to adjust commuting options.");
            }
        });

        view.findViewById(R.id.set_mode_heavy_traffic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.settingsProvider.saveLastDownloadedActions(application.settingsProvider.getSampleTrafficData());
                Utils.parseActionsAndSetAlarms(application, application.settingsProvider.getLastDownloadedActions());
                application.notificationService.DisplayNotification("Good morning!  Traffic is bad today!", "Your uber will arrive by 7:24AM");
            }
        });

        view.findViewById(R.id.set_mode_now).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.settingsProvider.saveLastDownloadedActions(application.settingsProvider.getSampleDataImmediately());
                Utils.parseActionsAndSetAlarms(application, application.settingsProvider.getLastDownloadedActions());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
