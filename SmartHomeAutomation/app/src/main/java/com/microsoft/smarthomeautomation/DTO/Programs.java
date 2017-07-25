package com.microsoft.smarthomeautomation.DTO;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Programs {

    public static final List<Program> ITEMS = new ArrayList<Program>();

    static {
        // Add some sample items.
        addItem(new Program("Wake up and get to work", "", true));
        addItem(new Program("Basic automation", "", true));
        addItem(new Program("Home comfort", "", false));
    }

    private static void addItem(Program item) {
        ITEMS.add(item);
    }

    public static class Program {
        public final String Name;
        public final String SerializedLogic;
        public boolean Enabled;

        public Program(String name, String serializedLogic, boolean enabled) {
            this.Name = name;
            this.SerializedLogic = serializedLogic;
            this.Enabled = enabled;
        }

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
    }
}
