package seedu.stockpal.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.storage.exception.InvalidStorageFilePathException;
import seedu.stockpal.storage.exception.StorageIOException;

public class StorageTest {
    @TempDir
    private static Path testDir;

    private static final String TEST_STORAGE_DIR = "src/test/data/StorageTest/";
    private static final String VALID_DATA_FILE_NAME = "ValidData.csv";
    private static final String INVALID_DATA_FILE_NAME = "InvalidData.csv";
    private static final String APPENDED_DATA_FILE_NAME = "AppendedData.csv";
    private static final String NON_EXISTENT_FILE_NAME = "NonExistentFile.csv";
    private static final String TEMP_FILE_NAME = "temp.csv";
    private static final String INVALID_FILE_NAME = "InvalidFileName";
    private static final String LINE_END = System.lineSeparator();

    private static final String PRODUCT1_NAME = "milk";
    private static final Integer PRODUCT1_QTY = 100;
    private static final Double PRODUCT1_PRICE = 3.0;
    private static final String PRODUCT1_DESC = "oat milk";
    private static final Integer PRODUCT1_PID = 1;

    private static final String PRODUCT2_NAME = "bread";
    private static final Integer PRODUCT2_QTY = 30;
    private static final Double PRODUCT2_PRICE = 1.5;
    private static final String PRODUCT2_DESC = null;
    private static final Integer PRODUCT2_PID = 2;

    private static final String PRODUCT3_NAME = "toothbrush";
    private static final Integer PRODUCT3_QTY = 10;
    private static final Double PRODUCT3_PRICE = null;
    private static final String PRODUCT3_DESC = null;
    private static final Integer PRODUCT3_PID = 3;

    private static final String PRODUCT4_NAME = "eggs";
    private static final Integer PRODUCT4_QTY = 120;
    private static final Double PRODUCT4_PRICE = null;
    private static final String PRODUCT4_DESC = "Organic eggs";
    private static final Integer PRODUCT4_PID = 4;


    /**
     * Tests if the constructor throws a NullPointerException if null is passed in as the file path.
     */
    @Test
    public void constructor_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Storage(null));
    }

    /**
     * Tests if the constructor throws an InvalidStorageFilePathException
     * if an invalid file name format is passed in as the file path.
     */
    @Test
    public void constructor_noCsvExtension_throwsInvalidStorageFilePathException() {
        assertThrows(InvalidStorageFilePathException.class, () ->
                new Storage(TEST_STORAGE_DIR  + INVALID_FILE_NAME));
    }

    /**
     * Tests if the load method throws a StorageIOException when a data file
     * with invalid data format is passed in.
     *
     * @throws Exception If the data file name is of an invalid format.
     */
    @Test
    public void load_invalidCsv_throwsStorageIOException() throws Exception {
        // The file contains valid CSV data, but does not match the Product format
        Storage storage = getStorage(INVALID_DATA_FILE_NAME);
        assertThrows(StorageIOException.class, storage::load);
    }

    /**
     * Tests if the load method loads a data file with valid data formats properly,
     * and checks if the loaded ProductList matches the expected ProductList.
     *
     * @throws Exception If the data file name is of an invalid format.
     */
    @Test
    public void load_validProductList() throws Exception {
        Storage testStorage = getStorage(VALID_DATA_FILE_NAME);
        ProductList pl = testStorage.load();
        ProductList testPL = getTestProductList();
        assertListsEquals(pl, testPL);
    }

    /**
     * Asserts that the loaded ProductList is the same as the expected ProductList.
     *
     * @param pl Loaded ProductList.
     * @param testPL Expected ProductList.
     */
    private void assertListsEquals(ProductList pl, ProductList testPL) {
        int plSize = pl.getSize();
        int testPLSize = testPL.getSize();
        assertEquals(plSize, testPLSize);
        for (int i = 0; i < plSize; i++) {
            assertProductEquals(pl.get(i), testPL.get(i));
        }
    }

    /**
     * Asserts that the Product in the loaded ProductList
     * is the same as the Product in the expected ProductList.
     *
     * @param actual Product in the loaded ProductList.
     * @param expected Product in the expected ProductList.
     */
    private void assertProductEquals(Product actual, Product expected) {
        assertEquals(actual.getName().toString(), expected.getName().toString());
        assertEquals(actual.getQuantity().toString(), expected.getQuantity().toString());
        assertEquals(actual.getPrice().toString(), expected.getPrice().toString());
        assertEquals(actual.getDescription().toString(), expected.getDescription().toString());
        assertEquals(actual.getPid().toString(), expected.getPid().toString());
    }

    /**
     * Tests if the loaded method creates a new data file of the given file path,
     * and returns a new empty ProductList.
     *
     * @throws Exception If the data file name is of the invalid format.
     */
    @Test
    public void load_nonExistentFile_createsNewFileAndReturnsEmptyProductList() throws Exception {
        ProductList actualPl = getStorage(NON_EXISTENT_FILE_NAME).load();
        ProductList expectedPl = new ProductList();

        assertEquals(actualPl.isEmpty(), expectedPl.isEmpty());

        // verify that loading non-existent file results in the file being created
        assertFileExist(TEST_STORAGE_DIR + NON_EXISTENT_FILE_NAME);
    }

    /**
     * Asserts that the created file exists in the desired file path.
     *
     * @param filePath The desired file path that the file should be at.
     */
    private void assertFileExist(String filePath) {
        assertTrue(Files.exists(Paths.get(filePath)));
    }

    /**
     * Tests if a new ProductList gets saved in the same format as the ValidData.csv file.
     *
     * @throws Exception If there is error reading from the file.
     */
    @Test
    public void save_validProductList() throws Exception {
        ProductList pl = getTestProductList();
        Storage storage = getTempStorage();
        storage.save(pl);

        assertStorageFilesEqual(storage, getStorage(VALID_DATA_FILE_NAME));
    }

    /**
     * Tests if a new Product gets appended in the correct format.
     *
     * @throws Exception If there is error reading from the file.
     */
    @Test
    public void append_validProduct() throws Exception {
        ProductList pl = getTestProductList();
        Storage storage = getTempStorage();
        storage.save(pl);

        Product toAdd =  new Product(PRODUCT4_NAME, PRODUCT4_QTY, PRODUCT4_PRICE,
                PRODUCT4_DESC, PRODUCT4_PID);
        storage.append(toAdd);

        assertStorageFilesEqual(storage, getStorage(APPENDED_DATA_FILE_NAME));
    }

    /**
     * Asserts that the contents of two storage files are the same.
     *
     * @param s1 First Storage file to compare.
     * @param s2 Second Storage file to compare.
     * @throws Exception If there is error reading from the files.
     */
    private void assertStorageFilesEqual(Storage s1, Storage s2) throws Exception {
        assertCsvFilesEqual(Paths.get(s1.getPath()), Paths.get(s2.getPath()));
    }

    /**
     * Asserts that the text in the two given files are the same. Ignores any
     * differences in line endings.
     *
     * @param path1 Path of the first Storage file to compare.
     * @param path2 Path of the second Storage file to compare.
     * @throws IOException If there is error reading from the files.
     */
    public static void assertCsvFilesEqual(Path path1, Path path2) throws IOException {
        List<String> list1 = Files.readAllLines(path1, Charset.defaultCharset());
        List<String> list2 = Files.readAllLines(path2, Charset.defaultCharset());
        assertEquals(String.join(LINE_END, list1), String.join(LINE_END, list2));
    }

    private Storage getStorage(String fileName) throws Exception {
        return new Storage(TEST_STORAGE_DIR + fileName);
    }

    private Storage getTempStorage() throws Exception {
        return new Storage(testDir.resolve(TEMP_FILE_NAME).toString());
    }

    private ProductList getTestProductList() {
        ProductList pl = new ProductList();
        pl.addProduct(new Product(PRODUCT1_NAME, PRODUCT1_QTY, PRODUCT1_PRICE,
                PRODUCT1_DESC, PRODUCT1_PID));
        pl.addProduct(new Product(PRODUCT2_NAME, PRODUCT2_QTY, PRODUCT2_PRICE,
                PRODUCT2_DESC, PRODUCT2_PID));
        pl.addProduct(new Product(PRODUCT3_NAME, PRODUCT3_QTY, PRODUCT3_PRICE,
                PRODUCT3_DESC, PRODUCT3_PID));
        return pl;
    }
}
