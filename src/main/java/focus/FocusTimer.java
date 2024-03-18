package focus;

import ui.Ui;

import java.time.Duration;
import java.time.LocalDateTime;

public class FocusTimer {
    private static final int MINUTES_DIVISION = 60;
    private static final int SECONDS_DIVISION = 60;
    public LocalDateTime startTiming;
    public LocalDateTime stopTiming;
    public boolean isStarted = false;

    public void setStartTiming() {
        this.startTiming = LocalDateTime.now();
        isStarted = true;
        Ui.printMessageWithSepNewLine("Your session has started. Time to grind!");
    }

    public void setStopTiming() {
        stopTiming = LocalDateTime.now();
        isStarted = false;
        totalTimeSpent();
    }

    public boolean getStatus() {
        return isStarted;
    }

    public void totalTimeSpent() {
        Duration timeElapsed = Duration.between(startTiming,stopTiming);
        long hours = timeElapsed.toHours();
        long minutes = timeElapsed.toMinutes() % MINUTES_DIVISION;
        long seconds = timeElapsed.toSeconds() % SECONDS_DIVISION;
        Ui.printMessageWithSepNewLine("Your focus session has ended.\n" + " Time spent: " +
                hours + " hours, " + minutes + " minutes, " + seconds + " seconds" + "\n" +
                "To start a new session, use ‘focus start’ ");
    }
}
