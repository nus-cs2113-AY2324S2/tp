package storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LogFileTest {

    @Test
    void initializeLogFile_noInput_logFileHandlerNotNull() {
        LogFile logTest = new LogFile();
        assertNotNull(logTest.logFileHandler);
    }
}
