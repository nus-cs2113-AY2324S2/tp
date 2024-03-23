package focus;

import ui.Ui;

/**
 * Represents the focus timer for wellness360
 */
public class FocusTimer {
    CountupTimer countupTimer;
    CountdownTimer countdownTimer;
    private boolean timerMode = false; // false for countup, true for countdown

    public FocusTimer() {
        this.countupTimer = new CountupTimer();
        this.countdownTimer = new CountdownTimer();
    }

    public void setStartTiming() {
        if(timerMode) {
            countdownTimer.setStart();
        } else {
            countupTimer.setStartTiming();
        }
    }

    public void setStopTiming() {
        if(timerMode){
            countdownTimer.setStop();
            Ui.printMessageWithSepNewLine("Countdown timer stopped.");
        } else {
            countupTimer.setStopTiming();
        }
    }

    public boolean getStatus() {
        if(timerMode) {
            return countdownTimer.getRunningStatus();
        } else {
            return countupTimer.getStartedStatus();
        }
    }
}
