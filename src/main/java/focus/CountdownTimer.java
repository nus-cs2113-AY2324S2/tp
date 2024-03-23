package focus;

import ui.Ui;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class CountdownTimer {
    private static final int DEFAULT_SECONDS = 59;
    private static final int DEFAULT_MINUTES = 0;
    private static final int ONE_SECOND = 1000;
    private static final int TIME_DELAY = 0;
    private static final int STOP_TIME = 0;
    private static final int START_WARNING = 5;
    private static final String ASSERTION_TIMER_NOT_RUNNING = "Timer should not be running";
    private static final String ASSERTION_INVALID_STOP = "Timer should be running.";
    private int minutes;
    private int seconds;
    private int inputMinutes;
    private boolean isStarted;
    private AtomicBoolean isCompleted;
    private AtomicBoolean isRunning;
    private Timer stopwatch;
    private TimerTask timerTask;

    public CountdownTimer() {
        this.minutes = DEFAULT_MINUTES;
        this.seconds = DEFAULT_SECONDS;
        this.isCompleted = new AtomicBoolean(false);
        this.isRunning = new AtomicBoolean(false);
        this.isStarted = false;
    }

    private void decreaseMinutes() {
        this.minutes--;
    }

    private void decreaseSeconds() {
        this.seconds--;
    }

    public void start() {
        assert !isRunning.get() : ASSERTION_TIMER_NOT_RUNNING;
        stopwatch = new Timer();
        isStarted = true;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (!isRunning.get()) {
                    return;
                }
                if(minutes == STOP_TIME && seconds == START_WARNING) {
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
                    seconds = DEFAULT_SECONDS;
                } else {
                    decreaseSeconds();
                }
            }
        };
        stopwatch.scheduleAtFixedRate(timerTask, TIME_DELAY, ONE_SECOND);
    }
    public void setStart() {
        isRunning.set(true);
        minutes = inputMinutes;
        start();
        Ui.printMessageWithSepNewLine("Countdown timer started");
    }
    public void setStop() {
        assert stopwatch != null : ASSERTION_INVALID_STOP;
        isCompleted.set(true);
        isRunning.set(false);
        stopwatch.cancel();
        stopwatch.purge();
    }
}
