package seedu.stockpal.storage;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import seedu.stockpal.exceptions.StockPalException;

public class CsvReader {

    private CSVReader csvReader;
    private CSVParser csvParser;

    public CsvReader(String filePath) throws StockPalException {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        }
        csvParser = new CSVParserBuilder().withSeparator(';').build();
        csvReader = new CSVReaderBuilder(br).withSkipLines(1).withCSVParser(csvParser).build();
    }

    public List<String[]> readAllData() throws StockPalException {
        try {
            return csvReader.readAll();
        } catch (IOException | CsvException e) {
            throw new StockPalException("Error reading data file");
        }
    }
}
