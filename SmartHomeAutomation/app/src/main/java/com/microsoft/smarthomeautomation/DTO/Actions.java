package com.microsoft.smarthomeautomation.DTO;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Actions {

    public static final List<Action> ACTIONS = new ArrayList<>();

    static {
        addItem(new Action("Turn lights up", "WakeUp", "", DateTime.now().plusHours(12), true, true));
        addItem(new Action("Make Home Comfortable", "Home", "", DateTime.now().plusHours(12).plusMinutes(30), true, true));
        addItem(new Action("Ordering Commute", "Commute", "", DateTime.now().plusHours(13), false, false));
    }

    private static void addItem(Action item) {
        ACTIONS.add(item);
    }

    public static class Action {
        public DateTime StartTime;
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
            this.StartTime = startTime;
        }
    }
}
