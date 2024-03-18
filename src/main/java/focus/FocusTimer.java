package focus;

import ui.Ui;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Represents the focus timer for wellness360
 */
public class FocusTimer {
    private static final int MINUTES_DIVISION = 60;
    private static final int SECONDS_DIVISION = 60;
    public LocalDateTime startTiming;
    public LocalDateTime stopTiming;
    public boolean isStarted = false;

    /**
     * Store the current time when the user calls the function as the start timing for the timer.
     */
    public void setStartTiming() {
        this.startTiming = LocalDateTime.now();
        isStarted = true;
        Ui.printMessageWithSepNewLine("Your session has started. Time to grind!");
    }

    /**
     *  Store the current time when the user calls the function as the stop timing for the timer.
     */
    public void setStopTiming() {
        stopTiming = LocalDateTime.now();
        isStarted = false;
        totalTimeSpent();
    }

    /**
     * Retrieves the current status of the clock
     *
     * @return The status of the focus timer
     */
    public boolean getStatus() {
        return isStarted;
    }

    /**
     *  Calculates the total time elapsed between the start timing and stop timing, and prints out
     *  the total time elapsed using Ui class.
     */
    public void totalTimeSpent() {
        Duration timeElapsed = Duration.between(startTiming, stopTiming);
        long hours = timeElapsed.toHours();
        long minutes = timeElapsed.toMinutes() % MINUTES_DIVISION;
        long seconds = timeElapsed.toSeconds() % SECONDS_DIVISION;
        Ui.printMessageWithSepNewLine("Your focus session has ended.\n" + " Time spent: " +
                hours + " hours, " + minutes + " minutes, " + seconds + " seconds" + "\n" +
                "To start a new session, use ‘focus start’ ");
    }
}
