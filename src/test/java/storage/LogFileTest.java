package storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LogFileTest {

    @Test
    void initializeLogFile_noInput_logFileHandlerNotNull() {
        LogFile logTest = LogFile.getInstance();
        assertNotNull(logTest.logFileHandler);
    }
}
