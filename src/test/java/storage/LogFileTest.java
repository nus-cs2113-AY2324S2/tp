package storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LogFileTest {
    static LogFile logTest = LogFile.getInstance();
    @Test
    void initializeLogFile_noInput_logFileHandlerNotNull() {
        assertNotNull(logTest.logFileHandler);
    }
}
