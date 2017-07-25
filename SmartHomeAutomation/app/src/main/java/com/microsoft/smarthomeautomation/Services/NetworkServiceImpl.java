package com.microsoft.smarthomeautomation.Services;

import android.content.Context;

import com.microsoft.smarthomeautomation.DTO.Actions;

public class NetworkServiceImpl implements NetworkService {

    private final Context context;

    public NetworkServiceImpl(Context context){
        this.context = context;
    }

    @Override
    public Actions FetchActions() {
        return null;
    }

    @Override
    public Void PutActions(Actions actions) {
        return null;
    }
}
