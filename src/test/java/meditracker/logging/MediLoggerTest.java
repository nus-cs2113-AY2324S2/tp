package meditracker.logging;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

public class MediLoggerTest {
    @Test
    public void getMediLogger_noInput_loggerReturned() {
        Logger log = MediLogger.getMediLogger();
        assertNotNull(log);
    }
}
