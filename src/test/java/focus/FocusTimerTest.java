package focus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FocusTimerTest {
    private FocusTimer focusTimer;

    @BeforeEach
    public void setUp() {
        focusTimer = new FocusTimer();
    }

    @Test
    public void setStartTimer_startTimer_success() {
        focusTimer.setStartTiming();
        assertEquals(true,focusTimer.getStatus());
    }

    @Test
    public void setStopTimer_stopTimer_success() {
        focusTimer.setStartTiming();
        focusTimer.setStopTiming();
        assertEquals(false, focusTimer.getStatus());
    }
}
