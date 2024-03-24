package focus;

import ui.Ui;

/**
 * Represents the focus timer for wellness360
 */
public class FocusTimer {
    CountupTimer countupTimer;
    CountdownTimer countdownTimer;
    private boolean timerMode = false; // false for count-up, true for countdown

    public FocusTimer() {
        this.countupTimer = new CountupTimer();
        this.countdownTimer = new CountdownTimer();
    }

    public void setStartTiming() {
        if (timerMode) {
            countdownTimer.setStart();
        } else {
            countupTimer.setStartTiming();
        }
    }

    public void setStopTiming() {
        if (timerMode) {
            countdownTimer.setStop();
            Ui.printMessageWithSepNewLine("Countdown timer stopped.");
        } else {
            countupTimer.setStopTiming();
        }
    }

    public boolean getStartStatus() {
        if (timerMode) {
            return countdownTimer.getRunningStatus();
        } else {
            return countupTimer.getStartedStatus();
        }
    }

    public void switchTimer() {
        this.timerMode = !timerMode;
        if (timerMode) {
            Ui.printMessageWithSepNewLine("Switched to Count down timer");
        } else {
            Ui.printMessageWithSepNewLine("Switched to Count up timer");
        }
    }

    public void setPauseTiming() {
        if (timerMode) {
            countdownTimer.setPause();
        } else {
            countupTimer.setPause();
        }
    }

    public boolean getPausedStatus() {
        if (timerMode) {
            return !countdownTimer.getPausedStatus();
        } else {
            return countupTimer.getPauseStatus();
        }
    }

    public void setResumeTiming() {
        if (timerMode) {
            countdownTimer.setResume();
        } else {
            countupTimer.setResume();
        }
    }

    public void setDuration(int commandArgs) {
        countdownTimer.setTimer(commandArgs);
    }

    public void checkTime() {
        if (timerMode) {
            countdownTimer.checkTime();
        }
        countupTimer.checkTime();
    }
}
