package focus;

import ui.Ui;

import java.time.LocalDateTime;

public class FocusTimer {
    public static LocalDateTime startTiming;
    public static LocalDateTime stopTiming;
    public static boolean isStarted = false;

    public static void setStartTiming() {
        startTiming = LocalDateTime.now();
        isStarted = true;
        Ui.printMessageWithSepNewLine("Your session has started. Time to grind!");
    }

    public static void setStopTiming() {
        stopTiming = LocalDateTime.now();
        isStarted = false;
    }
}
