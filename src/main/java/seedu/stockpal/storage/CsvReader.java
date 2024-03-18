package seedu.stockpal.storage;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import seedu.stockpal.exceptions.StockPalException;

import static seedu.stockpal.common.Messages.MESSAGE_FILE_ALR_CREATED;
import static seedu.stockpal.common.Messages.WARNING_READ_DATA_ERROR;

/**
 * Represents the CsvReader object for the StockPal application.
 * Responsible for reading in data from the saved data file.
 */
public class CsvReader {

    private static final Character SEPARATOR = ';';
    private static final int NUM_HEADER_ROWS = 1;

    private final CSVReader csvReader;

    /**
     * Constructs a new CsvReader object and creates a reader to read in data
     * from the saved data file in filePath.
     *
     * @param filePath Path to the saved data file.
     */
    public CsvReader(String filePath) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError(MESSAGE_FILE_ALR_CREATED);
        }
        CSVParser csvParser = new CSVParserBuilder().withSeparator(SEPARATOR).build();
        csvReader = new CSVReaderBuilder(br).withSkipLines(NUM_HEADER_ROWS)
                .withCSVParser(csvParser).build();
    }

    /**
     * Reads in all data at once from the saved data file.
     *
     * @return Returns a List of String Arrays where each Array contains
     *     the entire row of data of a product.
     * @throws StockPalException If there is an error reading data from the saved data file.
     */
    public List<String[]> readAllData() throws StockPalException {
        try {
            return csvReader.readAll();
        } catch (IOException | CsvException e) {
            throw new StockPalException(WARNING_READ_DATA_ERROR);
        }
    }
}
