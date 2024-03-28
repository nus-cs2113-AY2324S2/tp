package focus;

import ui.Ui;

/**
 * Represents the focus timer for wellness360.
 */
public class FocusTimer {
    private CountupTimer countupTimer;
    private CountdownTimer countdownTimer;
    private boolean timerMode = false; // false for count-up, true for countdown

    /**
     * Constructs a FocusTimer class with instances of CountupTimer and CountdownTimer
     */
    public FocusTimer() {
        this.countupTimer = new CountupTimer();
        this.countdownTimer = new CountdownTimer();
    }

    /**
     * Set the start timing for the timer.
     */
    public void setStartTiming() {
        if (timerMode) {
            countdownTimer.setStart();
        } else {
            countupTimer.setStartTiming();
        }
    }

    /**
     * Set the stop timing for the timer.
     */
    public void setStopTiming() {
        if (timerMode) {
            countdownTimer.setStop();
            Ui.printMessageWithSepNewLine("Countdown timer stopped.");
        } else {
            countupTimer.setStopTiming();
        }
    }

    /**
     * Check whether the timer has started.
     *
     * @return true if the timer has started. False otherwise.
     */
    public boolean getStartStatus() {
        if (timerMode) {
            return countdownTimer.getRunningStatus();
        } else {
            return countupTimer.getStartedStatus();
        }
    }

    /**
     * Switch the timer mode for focus timer between count yp timer and countdown timer.
     */
    public void switchTimer() {
        this.timerMode = !timerMode;
        if (timerMode) {
            Ui.printMessageWithSepNewLine("Switched to Count down timer");
        } else {
            Ui.printMessageWithSepNewLine("Switched to Count up timer");
        }
    }

    /**
     * Pause the timer that is currently running.
     */
    public void setPauseTiming() {
        if (timerMode) {
            countdownTimer.setPause();
        } else {
            countupTimer.setPause();
        }
    }

    /**
     * Get the pause status of the current timer that is running.
     *
     * @return True if the timer is in the paused state. False otherwise.
     */
    public boolean getPausedStatus() {
        if (timerMode) {
            return !countdownTimer.getPausedStatus();
        } else {
            return countupTimer.getPauseStatus();
        }
    }

    /**
     * Resume the timer that is currently running.
     */
    public void setResumeTiming() {
        if (timerMode) {
            countdownTimer.setResume();
        } else {
            countupTimer.setResume();
        }
    }

    /**
     * Set the desired duration for the countdown timer.
     *
     * @param commandArgs The desired number of minutes from the user.
     */
    public void setDuration(int commandArgs) {
        countdownTimer.setTimer(commandArgs);
    }

    /**
     * Check the total time elapsed for count up timer or time remaining for countdown timer.
     */
    public void checkTime() {
        if (timerMode) {
            countdownTimer.checkTime();
        } else {
            countupTimer.checkTime();
        }
    }
}
