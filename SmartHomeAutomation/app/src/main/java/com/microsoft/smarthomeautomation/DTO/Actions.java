package com.microsoft.smarthomeautomation.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Actions {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Action> ACTIONS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Action> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 25;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Action item) {
        ACTIONS.add(item);
        ITEM_MAP.put(item.Type, item);
    }

    private static Action createDummyItem(int position) {
        return new Action(String.valueOf(position), "Item " + position, makeDetails(position), false);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class Action {
        public final String ReadableName;
        public final String Type;
        public final String Value;
        public final boolean Acknowledged;

        public Action(String ReadableName, String Type, String Value, boolean Acknowledged) {
            this.ReadableName = ReadableName;
            this.Type = Type;
            this.Value = Value;
            this.Acknowledged = Acknowledged;
        }
    }
}
