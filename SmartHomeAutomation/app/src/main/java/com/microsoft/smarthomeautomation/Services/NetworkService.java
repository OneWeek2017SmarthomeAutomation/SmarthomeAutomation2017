package com.microsoft.smarthomeautomation.Services;

import com.microsoft.smarthomeautomation.DTO.Actions;

public interface NetworkService {

    Actions FetchActions();
    Void PutActions(Actions actions);
}
