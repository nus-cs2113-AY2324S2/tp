package seedu.duke;


import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.ArrayList;
/**
 * Class for reading & writing input/output to file
 */
public class Storage {
    private static String filePath = "../../../../../tasklist.txt";

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static List<Record> records = new ArrayList<>();

    public static void addRecord(Record record) {
        records.add(record);
    }

    public static void removeRecord(Record record) {
        records.remove(record);
    }

    public static void clearRecords() {
        records.clear();
    }


    /**
     * Method for processing a line of input
     * @param line the line to be processed
     * @throws Exception exception is thrown whenever the input format is corrupt.
     */
    public static void processLine(String line) throws Exception{
        String[] words = line.split(" ");
        String suffixWord = line.substring(words[0].length() + 1);

        if (words.length != 3 ) {
            throw new Exception();
        }

        LocalDateTime dateTime = LocalDateTime.parse(words[0], formatter);
        double speed = Double.parseDouble(words[1]);
        double accuracy = Double.parseDouble(words[2]);

        Record record = new Record(dateTime, speed, accuracy);
        records.add(record);
    }

    /**
     * Method for reading the input file
     */
    public static void readFile() {
        try (BufferedReader reader= new BufferedReader(new FileReader(filePath))){
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    processLine(line);
                }
            } catch (Exception e) {
                records.clear();
            }
        } catch (IOException e) {
            records.clear();
        }
    }

    /**
     * Method for writing all your previous records to the file
     */
    public static void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Record record : records) {
                writer.write(record.writeLine());
                writer.newLine();
            }
            writer.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

