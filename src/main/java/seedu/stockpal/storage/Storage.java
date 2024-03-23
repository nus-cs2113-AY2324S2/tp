package seedu.stockpal.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import seedu.stockpal.commands.Command;
import seedu.stockpal.commands.HelpCommand;
import seedu.stockpal.commands.NewCommand;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.exception.InvalidStorageFilePathException;
import seedu.stockpal.storage.exception.StorageIOException;

import static seedu.stockpal.common.Messages.ERROR_MESSAGE_GENERAL;
import static seedu.stockpal.common.Messages.WARNING_DATA_ERROR;
import static seedu.stockpal.common.Messages.WARNING_INVALID_FILEPATH;


/**
 * Represents the file storage of the StockPal application.
 * Responsible for loading/saving of the inventory data from/to the CSV file.
 */
public class Storage {

    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());
    private static final String DEFAULT_STORAGE_FILEPATH = "data/inventory.csv";
    private static final String EMPTY_STRING = "";
    private static final Double EMPTY_PRICE = -0.1;

    private static final int PID_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int QTY_INDEX = 2;
    private static final int PRICE_INDEX = 3;
    private static final int DESC_INDEX = 4;
    private static final int WITH_PRICE_LEN = 4;
    private static final int WITH_DESC_LEN = 5;

    private final String path;
    private final boolean isAppend = true;
    private CsvWriter csvWriter;

    /**
     * Constructs a new Storage object with the default storage filepath.
     *
     * @throws InvalidStorageFilePathException If the file path is invalid.
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Constructs a new Storage object with the specified filepath.
     *
     * @param filePath The filepath of the saved CSV file containing the products in the inventory.
     * @throws InvalidStorageFilePathException If the file path is invalid.
     */
    public Storage(String filePath) throws InvalidStorageFilePathException {
        if (!isValidPath(filePath)) {
            throw new InvalidStorageFilePathException(WARNING_INVALID_FILEPATH);
        }
        this.path = filePath;
        setupLogger();
    }


    /**
     * Checks if the given file path is of the valid format.
     *
     * @param filePath The given file path string.
     * @return true if the file path is of the correct extension, else false.
     */
    private boolean isValidPath(String filePath) {
        return filePath.endsWith(".csv");
    }

    /**
     * Loads the product list from the saved data file and returns the loaded product list.
     *
     * @return Loaded product list with the previously saved products.
     * @throws StockPalException If an error occurred while creating the new save file.
     * @throws StorageIOException If the data file contains any erroneous input.
     */
    public ProductList load() throws StockPalException, StorageIOException {
        LOGGER.entering(getClass().getName(), "load");
        Path filepath = Path.of(this.path);
        if (!Files.exists(filepath) || !Files.isRegularFile(filepath)) {
            createSaveFile();
            LOGGER.info("Creating new save file");
            return new ProductList();
        }
        CsvReader invReader = new CsvReader(this.path);
        LOGGER.exiting(getClass().getName(), "load");
        return parseInventory(invReader.readAllData());
    }

    /**
     * Parses the entire data file and returns the data as a product list.
     *
     * @param csvData The list of String Arrays where each Array contains
     *                the entire row of data of a product.
     * @return Loaded product list with the previously saved products.
     * @throws StorageIOException If the data file contains any erroneous input.
     */
    private ProductList parseInventory(List<String[]> csvData) throws StorageIOException {
        LOGGER.entering(getClass().getName(), "parseInventory");
        ProductList productList = new ProductList();
        for (String[] productRow : csvData) {
            try {
                productList.addProduct(parseProductFromRow(productRow));
            } catch (NullPointerException | NumberFormatException e) {
                LOGGER.log(Level.SEVERE, "Error in data file", e);
                throw new StorageIOException(WARNING_DATA_ERROR);
            }
        }
        LOGGER.exiting(getClass().getName(), "parseInventory");
        return productList;
    }

    /**
     * Parses the data row by row and converts each row into a Product.
     *
     * @param productRow The Array containing the details of the saved Product in the data file.
     * @return The converted Product.
     * @throws NumberFormatException If any of the Strings does not contain a parsable Integer/Double.
     * @throws NullPointerException If the Price String is null.
     */
    private Product parseProductFromRow(String[] productRow)
            throws NumberFormatException, NullPointerException {
        LOGGER.entering(getClass().getName(), "parseProductFromRow");
        Integer productId = Integer.parseInt(productRow[PID_INDEX].trim());
        String productName = productRow[NAME_INDEX].trim();
        Integer productQty = Integer.parseInt(productRow[QTY_INDEX].trim());
        Double productPrice = isEmptyPrice(productRow)
                ? EMPTY_PRICE
                : Double.parseDouble(productRow[PRICE_INDEX].trim());
        String productDesc = isEmptyDesc(productRow)
                ? EMPTY_STRING
                : productRow[DESC_INDEX].trim();
        LOGGER.exiting(getClass().getName(), "parseProductFromRow");
        return new Product(productName, productQty, productPrice, productDesc, productId);
    }

    /**
     * Checks if Price of the Product is empty.
     *
     * @param productRow Array containing the details of the product.
     * @return true if price of the product is non-existent, else false.
     */
    private boolean isEmptyPrice(String[] productRow) {
        return productRow.length < WITH_PRICE_LEN ||
                productRow[PRICE_INDEX].equalsIgnoreCase(EMPTY_STRING);
    }

    /**
     * Checks if Description of the Product is empty.
     *
     * @param productRow Array containing the details of the product.
     * @return true if description of the product is non-existent, else false.
     */
    private boolean isEmptyDesc(String[] productRow) {
        return productRow.length < WITH_DESC_LEN;
    }

    /**
     * Executes the corresponding save/append function based on the current Command.
     *
     * @param command The current Command being executed.
     * @param productList The updated ProductList.
     * @throws StockPalException If there is an error saving the data.
     */
    public void saveData(Command command, ProductList productList) throws StockPalException {
        LOGGER.entering(getClass().getName(), "saveData");
        if (command instanceof HelpCommand) {
            return;
        }
        if (command instanceof NewCommand) {
            int idxNewProd = productList.getSize() - 1;
            Product newProduct = productList.get(idxNewProd);
            append(newProduct);
        } else {
            save(productList);
        }
        LOGGER.exiting(getClass().getName(), "saveData");
    }

    /**
     * Saves the current ProductList to the data file.
     *
     * @param productList The ProductList containing the data to be saved.
     * @throws StockPalException If there is an error saving the data.
     */
    protected void save(ProductList productList) throws StockPalException {
        LOGGER.entering(getClass().getName(), "save");
        csvWriter = new CsvWriter(this.path, !isAppend);
        csvWriter.saveAllData(productList);
        LOGGER.exiting(getClass().getName(), "save");
    }

    /**
     * Appends the new Product to the next line of the data file.
     *
     * @param newProduct The new Product to be appended.
     * @throws StockPalException If there is an error saving the data.
     */
    protected void append(Product newProduct) throws StockPalException {
        LOGGER.entering(getClass().getName(), "append");
        csvWriter = new CsvWriter(this.path, isAppend);
        csvWriter.appendProduct(newProduct);
        LOGGER.exiting(getClass().getName(), "append");
    }

    /**
     * Creates the new save file with the specified file path.
     *
     * @throws StockPalException If an error occurred while creating the new save file.
     */
    private void createSaveFile() throws StockPalException {
        LOGGER.entering(getClass().getName(), "createSaveFile");
        File f = new File(path);
        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, "Error creating new data file", ioe);
            throw new StockPalException(ERROR_MESSAGE_GENERAL + ioe.getMessage());
        }
        LOGGER.exiting(getClass().getName(), "createSaveFile");
    }

    /**
     * Sets up Logger for the logging of the Storage class.
     */
    private void setupLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        LOGGER.addHandler(consoleHandler);
        try {
            FileHandler fileHandler = new FileHandler("StorageLogger.log");
            LOGGER.addHandler(fileHandler);
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, "File logger not loading", ioe);
        }
    }

    /**
     * Gets the String representation of the filepath for testing.
     *
     * @return String representation of the filepath.
     */
    protected String getPath() {
        return path;
    }
}
