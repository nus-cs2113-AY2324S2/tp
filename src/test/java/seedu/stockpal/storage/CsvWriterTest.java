package seedu.stockpal.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CsvWriterTest {

    /**
     * Tests if the constructor throws a NullPointerException if null is passed in as the file path.
     */
    @Test
    public void constructor_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CsvWriter(null, false));
    }
}
