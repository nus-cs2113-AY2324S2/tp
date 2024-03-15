package storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import storage.Storage;

public class StorageTest {

    /*
     * The StorageTest class tests the functionality of the Storage class.
     * More specifically, the load method of the Storage class is under testing.
     */

    @Test
    public void testLoad() {
        Storage storage = Storage.load(123);
        Assertions.assertNotNull(storage, "Checking if a loaded Storage object is not null");

        // You can add more assertions depending on the actual behaviour 
        // of the load method in the Storage class.
    }
}