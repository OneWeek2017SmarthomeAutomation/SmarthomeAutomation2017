package com.microsoft.smarthomeautomation.Services;

import com.microsoft.smarthomeautomation.DTO.Action;

import java.util.ArrayList;

public interface NetworkService {

    ArrayList<Action> FetchActions();
    Void PutActions(ArrayList<Action> actions);

    void setLightBrightness(int brightness);

}
