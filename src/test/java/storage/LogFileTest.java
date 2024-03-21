package storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LogFileTest {
    static LogFile logTest = LogFile.getInstance();

    /**
     * Tests the behaviour of the getInstance function in the LogFile class, and whether
     * it returns a non-null instance.
     */
    @Test
    void initializeLogFile_noInput_logFileHandlerNotNull() {
        assertNotNull(LogFile.logFileHandler);
    }
}
