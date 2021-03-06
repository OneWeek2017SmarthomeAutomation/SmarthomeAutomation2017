package com.microsoft.smarthomeautomation.DTO;

import org.joda.time.DateTime;

import java.util.Date;

public class Action {
    public Date StartTime;
    public String ReadableName;
    public String Type;
    public Object Value;
    public int Id;
    public boolean Acknowledged;
    public boolean Enabled;

    public Action(String ReadableName, String Type, String Value, DateTime startTime, boolean Enabled, boolean Acknowledged) {
        this.ReadableName = ReadableName;
        this.Type = Type;
        this.Value = Value;
        this.Acknowledged = Acknowledged;
        this.Enabled = Enabled;
        this.StartTime = startTime.toDate();
    }
}
