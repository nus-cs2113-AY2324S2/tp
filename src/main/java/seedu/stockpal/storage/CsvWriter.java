package seedu.stockpal.storage;

import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.exceptions.StockPalException;

import static seedu.stockpal.common.Messages.WARNING_CLOSE_WRITER_ERROR;
import static seedu.stockpal.common.Messages.WARNING_DATA_FILE_FORMAT_ERROR;

/**
 * Represents the CsvWriter object for the StockPal application.
 * Responsible for writing data to the saved data file.
 */
public class CsvWriter {

    private static final String HEADER_STRING = "PID;Name;Quantity;Price;Description";
    private static final Character SEPARATOR = ';';
    private static final String LINE_END = System.lineSeparator();

    private final CSVWriter csvWriter;

    /**
     * Constructs a new CsvWriter object and creates a writer to write data
     * into the saved data file in filePath.
     *
     * @param filePath Path to the saved data file.
     */
    public CsvWriter(String filePath, boolean isAppend) throws StockPalException {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(filePath, isAppend));
        } catch (IOException ioe) {
            throw new StockPalException(WARNING_DATA_FILE_FORMAT_ERROR);
        }
        csvWriter = new CSVWriter(bw, SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                LINE_END);
    }

    /**
     * Edits and saves all product data at once into the data file.
     *
     * @param productList The ProductList containing all the products to be saved.
     * @throws StockPalException If there is an error saving the data.
     */
    public void saveAllData(ProductList productList) throws StockPalException {
        List<String[]> data = new ArrayList<>();
        int numOfProdRows = productList.getSize();
        String[] header = HEADER_STRING.split(SEPARATOR.toString());
        data.add(header);

        for (int i = 0; i < numOfProdRows; i++) {
            String prodRow = productList.toSave(i);
            String[] rowData = prodRow.split(SEPARATOR.toString());
            data.add(rowData);
        }

        csvWriter.writeAll(data);
        try {
            csvWriter.close();
        } catch (IOException ioe) {
            throw new StockPalException(WARNING_CLOSE_WRITER_ERROR);
        }
    }

    /**
     * Appends the new Product to the data file.
     *
     * @param newProduct The new Product to be appended.
     * @throws StockPalException If there is an error saving the data.
     */
    public void appendProduct(Product newProduct) throws StockPalException {
        String prodRow = newProduct.toSave();
        String[] rowData = prodRow.split(SEPARATOR.toString());
        csvWriter.writeNext(rowData);
        try {
            csvWriter.close();
        } catch (IOException ioe) {
            throw new StockPalException(WARNING_CLOSE_WRITER_ERROR);
        }
    }
}
