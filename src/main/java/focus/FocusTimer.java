package focus;

import ui.Ui;

import java.time.Duration;
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
        totalTimeSpent();
    }

    public static void totalTimeSpent() {
        Duration timeElapsed = Duration.between(startTiming,stopTiming);
        long hours = timeElapsed.toHours();
        long minutes = timeElapsed.toMinutes();
        long seconds = timeElapsed.toSeconds();
        Ui.printMessageWithSepNewLine("Your focus session has ended.\n" + " Time spent: " +
                hours + " hours, " + minutes + " minutes, " + seconds + " seconds" + "\n" +
                "To start a new session, use ‘focustimer start’ ");
    }
}
