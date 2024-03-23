package focus;

import ui.Ui;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class CountdownTimer {
    private static final int DEFAULT_SECONDS = 0;
    private static final int DEFAULT_MINUTES = 1;
    private static final int ONE_SECOND = 1000;
    private static final int MAX_SECONDS = 59;
    private static final int TIME_DELAY = 0;
    private static final int STOP_TIME = 0;
    private static final int START_WARNING = 5;
    private static final String ASSERTION_TIMER_NOT_RUNNING = "Timer should not be running";
    private static final String ASSERTION_INVALID_STOP = "Timer should be running.";
    private int minutes;
    private int seconds;
    private int inputMinutes;
    private AtomicBoolean isStarted;
    private AtomicBoolean isRunning;
    private Timer stopwatch;
    private TimerTask timerTask;

    public CountdownTimer() {
        this.minutes = DEFAULT_MINUTES;
        this.seconds = DEFAULT_SECONDS;
        this.inputMinutes = DEFAULT_MINUTES;
        this.isRunning = new AtomicBoolean(false);
        this.isStarted = new AtomicBoolean(false);
    }

    private void decreaseMinutes() {
        this.minutes--;
    }

    private void decreaseSeconds() {
        this.seconds--;
    }

    public void start() {
        stopwatch = new Timer();
        isStarted.set(true);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (!isRunning.get()) {
                    return;
                }
                if (minutes == STOP_TIME && seconds == START_WARNING) {
                    System.out.println();
                }
                if (minutes == STOP_TIME && seconds <= START_WARNING && seconds != STOP_TIME) {
                    Ui.printMessageWithSepNewLine(seconds + " seconds left");
                }
                if (minutes == STOP_TIME && seconds == STOP_TIME) {
                    Ui.printMessageWithSepNewLine("Count down timer completed!");
                    setStop();
                    Ui.promptUserInput();
                } else if (seconds == STOP_TIME) {
                    decreaseMinutes();
                    seconds = MAX_SECONDS;
                } else {
                    decreaseSeconds();
                }
            }
        };
        stopwatch.scheduleAtFixedRate(timerTask, TIME_DELAY, ONE_SECOND);
    }

    public void setStart() {
        assert !isRunning.get() : ASSERTION_TIMER_NOT_RUNNING;
        isRunning.set(true);
        minutes = inputMinutes;
        start();
        Ui.printMessageWithSepNewLine("Countdown timer started! \n"
                + "Duration set: " + minutes + " minute(s) " + seconds + " second(s)");
    }

    public void setStop() {
        assert stopwatch != null : ASSERTION_INVALID_STOP;
        isRunning.set(false);
        isStarted.set(false);
        stopwatch.cancel();
        stopwatch.purge();
    }

    public void setPause() {
        isRunning.set(false);
        Ui.printMessageWithSepNewLine("Timer paused. \n"  +
               "Remaining time: " + minutes + " minutes " + seconds + " seconds");
    }

    public void setResume() {
        isRunning.set(true);
        Ui.printMessageWithSepNewLine("Countdown timer resumed.");
    }

    public boolean getRunningStatus() {
        return isStarted.get();
    }

    public boolean getPausedStatus() {
        return isRunning.get();
    }
}
