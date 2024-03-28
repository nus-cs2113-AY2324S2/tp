package focus;

import ui.Ui;

import java.time.Duration;
import java.time.LocalDateTime;

public class CountupTimer {
    private static final int MINUTES_DIVISION = 60;
    private static final int SECONDS_DIVISION = 60;
    private LocalDateTime startTiming;
    private LocalDateTime stopTiming;
    private LocalDateTime currentTime;
    private boolean isStarted = false;
    private boolean isPaused = false;
    private long totalHours = 0;
    private long totalMinutes = 0;
    private long totalSeconds = 0;

    /**
     * Store the current time when the user calls the function as the start timing for the timer.
     */
    public void setStartTiming() {
        assert !isStarted : "Timer should not have started";
        this.startTiming = LocalDateTime.now();
        isStarted = true;
        Ui.printMessageWithSepNewLine("Your session has started. Time to grind!");
        this.totalHours = 0;
        this.totalMinutes = 0;
        this.totalSeconds = 0;
    }

    /**
     * Store the current time when the user calls the function as the stop timing for the timer.
     */
    public void setStopTiming() {
        assert isStarted : "Timer should have started";
        if(!isPaused) {
            stopTiming = LocalDateTime.now();
        }
        isStarted = false;
        isPaused = false;
        totalTimeSpent();
    }

    /**
     * Set the timer to pause
     */
    public void setPause() {
        currentTime = LocalDateTime.now();
        Duration timeElapsed = Duration.between(startTiming, currentTime);
        stopTiming = LocalDateTime.now();
        isPaused = true;
        totalHours += timeElapsed.toHours();
        totalMinutes += timeElapsed.toMinutes() % MINUTES_DIVISION;
        totalSeconds += timeElapsed.toSeconds() % MINUTES_DIVISION;
        Ui.printMessageWithSepNewLine("Count up timer paused.");
    }

    /**
     * Set the timer to resume.
     */
    public void setResume() {
        this.startTiming = LocalDateTime.now();
        isPaused = false;
        Ui.printMessageWithSepNewLine("Count up timer resumed");
    }

    /**
     * Get the current paused status of the timer
     *
     * @return true if the timer has been paused, false otherwise.
     */
    public boolean getPauseStatus() {
        return isPaused;
    }

    /**
     * Retrieves the current status of the clock
     *
     * @return The status of the focus timer
     */
    public boolean getStartedStatus() {
        return isStarted;
    }

    /**
     * Calculates the total time elapsed between the start timing and stop timing, and prints out
     * the total time elapsed using Ui class.
     */
    public void totalTimeSpent() {
        if (!isPaused) {
            Duration timeElapsed = Duration.between(startTiming, stopTiming);
            totalHours += timeElapsed.toHours();
            totalMinutes += timeElapsed.toMinutes() % MINUTES_DIVISION;
            totalSeconds += timeElapsed.toSeconds() % SECONDS_DIVISION;
        }
        Ui.printMessageWithSepNewLine("Your focus session has ended.\n" + "Total time spent: " +
                totalHours + " hours, " + totalMinutes + " minutes, " + totalSeconds + " seconds" + "\n" +
                "To start a new session, use ‘focus start’ ");
    }

    /**
     * Check to total time elapsed from the start to the current time, and prints out the total time elapsed
     * using the Ui class.
     */
    public void checkTime() {
        if(!isPaused) {
            currentTime = LocalDateTime.now();
        }
        Duration timeElapsed = Duration.between(startTiming, currentTime);
        startTiming = currentTime;
        totalHours += timeElapsed.toHours();
        totalMinutes += timeElapsed.toMinutes() % MINUTES_DIVISION;
        totalSeconds += timeElapsed.toSeconds() % SECONDS_DIVISION;
        Ui.printMessageWithSepNewLine("Total time elapsed: \n" +
                totalHours + " hours, " + totalMinutes + " minutes, " + totalSeconds + " seconds");
    }
}
