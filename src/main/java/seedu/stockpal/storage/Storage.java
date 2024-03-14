package seedu.stockpal.storage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.exception.StorageIOException;

public class Storage {

    public static final String DEFAULT_STORAGE_FILEPATH = "data/inventory.csv";
    private static final Double EMPTY_PRICE = -0.1;
    private static final String EMPTY_STRING = "";

    public final String path;

    public Storage() {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath) {
        this.path = filePath;
    }

    public ProductList load() throws StockPalException, StorageIOException {
        Path filepath = Path.of(this.path);
        if (!Files.exists(filepath) || !Files.isRegularFile(filepath)) {
            createSaveFile();
            return new ProductList();
        }
        CsvReader invReader = new CsvReader(this.path);
        return parseInventory(invReader.readAllData());
    }

    private ProductList parseInventory(List<String[]> csvData) throws StorageIOException {
        ProductList productList = new ProductList();
        for (String[] productRow : csvData) {
            try {
                productList.addProduct(parseProductFromRow(productRow));
            } catch (NumberFormatException e) {
                throw new StorageIOException("Data file contains erroneous input");
            }
        }
        return productList;
    }

    private Product parseProductFromRow(String[] productRow) throws NumberFormatException {
        Integer productId = Integer.parseInt(productRow[0]);
        String productName = productRow[1];
        Integer productQty = Integer.parseInt(productRow[2]);
        Double productPrice = (productRow[3].equalsIgnoreCase(EMPTY_STRING))
                ? EMPTY_PRICE
                : Double.parseDouble(productRow[3]);
        String productDesc = productRow[4];
        return new Product(productName, productQty, productPrice, productDesc, productId);
    }

    private void createSaveFile() throws StockPalException {
        File f = new File(path);
        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new StockPalException("Error Occurred: " + e.getMessage());
        }
    }
}
