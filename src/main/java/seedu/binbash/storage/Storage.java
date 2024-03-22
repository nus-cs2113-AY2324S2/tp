package seedu.binbash.storage;

import seedu.binbash.item.Item;
import seedu.binbash.command.AddCommand;
import seedu.binbash.exceptions.BinBashException;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.RetailItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

public class Storage {
    private static final DateTimeFormatter EXPECTED_INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    protected String filePath;
    protected String dataDirectoryPath;
    protected String dataFileName;
    protected boolean isCorrupted;
    protected Logger storageLogger;

    public Storage() {
        this.filePath = "data/items.txt";
        this.dataDirectoryPath = "./data/";
        this.dataFileName = "items.txt";
        this.isCorrupted = false; // set to false by default}
        this.storageLogger = Logger.getLogger("storageLogger");
    }

    // TODO: Handle exceptions properly (when the exceptions for AddCommand are settled)
    //  Lumping 3 exceptions together without a custom message for each
    //  exception is bad exception handling!

    /**
     * Loads the data from the file and returns a list of items.
     * If the data file is corrupted or an error occurs during reading, the file is marked as corrupted.
     *
     * @return A list of items loaded from the file.
     * @throws RuntimeException if an error occurs during file reading.
     */
    public ArrayList<Item> loadData() {
        storageLogger.log(Level.INFO, "Preparing to load data from storage file.");

        ArrayList<Item> itemList = null;

        try {
            ArrayList<String> stringRepresentationOfTxtFile = readTxtFile();
            itemList = parseAndAddToList(stringRepresentationOfTxtFile);
        } catch (BinBashException | IOException | NumberFormatException e) {
            isCorrupted = true;
        }

        assert !isCorrupted : "data file is corrupted";

        storageLogger.log(Level.INFO, "Data loaded successfully.");

        return itemList;
    }

    /**
     * Reads the data file and returns a list of strings representing each line in the file.
     *
     * @return A list of strings, each representing a line in the data file.
     * @throws BinBashException if the directory or file cannot be created.
     * @throws IOException if an error occurs during file reading.
     */
    private ArrayList<String> readTxtFile() throws BinBashException, IOException {
        File dataDirectory = new File(dataDirectoryPath);
        File dataFile = new File(dataDirectory, dataFileName);

        // Checks if the 'data' directory exists, if not create it
        if (!dataDirectory.exists()) {
            boolean wasDirectoryMade = dataDirectory.mkdirs();
            if (!wasDirectoryMade) {
                throw new BinBashException("Could not create data directory.");
            }
        }

        // Checks if the 'tasks.txt' file exists, if not create it
        if (!dataFile.exists()) {
            boolean wasFileCreated = dataFile.createNewFile();
            if (!wasFileCreated) {
                throw new BinBashException("Could not create items.txt file.");
            }
        }

        assert dataDirectory.exists() : "Data directory should already exist / have been created";
        assert dataFile.exists() : "Data file (items.txt) should already exist / have been created";

        ArrayList<String> dataItems = (ArrayList<String>)
                Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
        return dataItems;
    }

    // TODO: Check if there's a better way to do this. It would be nice to use methods from the
    //  Parser class and create AddCommand(s) so that I can call Command.execute(), but that requires
    //  me to already have an ItemList object, which would be weird since loadData() returns an Item List.

    // TODO: Perhaps I could refactor it to create an ItemList object first, then
    //  call Storage.loadData() ?

    /**
     * Parses the list of strings and converts them into a list of Item objects.
     *
     * @param stringRepresentationOfTxtFile A list of strings representing the storage file.
     * @return A list of Item objects created from the parsed data.
     */
    private ArrayList<Item> parseAndAddToList(ArrayList<String> stringRepresentationOfTxtFile) {
        ArrayList<Item> itemList = new ArrayList<Item>();

        for (String line: stringRepresentationOfTxtFile) {

            if (line == null) {
                break;
            }

            Matcher matcher = AddCommand.COMMAND_FORMAT.matcher(line);
            if (matcher.matches()) {
                String itemName = matcher.group("itemName");
                String itemDescription = matcher.group("itemDescription");
                int itemQuantity = Integer.parseInt(
                        Objects.requireNonNullElse(matcher.group("itemQuantity"), "0").strip()
                );
                String itemExpirationDate = Objects.requireNonNullElse(
                        matcher.group("itemExpirationDate"),
                        "N.A."
                ).strip();
                double itemSalePrice = Double.parseDouble(matcher.group("itemSalePrice"));
                double itemCostPrice = Double.parseDouble(matcher.group("itemCostPrice"));

                if (itemExpirationDate.equals("N.A.")) {
                    itemList.add(new RetailItem(
                            itemName,
                            itemDescription,
                            itemQuantity,
                            Optional.empty(),
                            itemSalePrice,
                            itemCostPrice));
                } else {
                    itemList.add(new PerishableRetailItem(
                            itemName,
                            itemDescription,
                            itemQuantity,
                            Optional.of(LocalDate.parse(itemExpirationDate, EXPECTED_INPUT_DATE_FORMAT)),
                            itemSalePrice,
                            itemCostPrice));
                }
            } else {
                isCorrupted = true;
            }
        }
        return itemList;
    }

    /**
     * Saves the list of items to the storage file.
     *
     * @param itemList The list of items to be saved.
     */
    public void saveToStorage(List<Item> itemList) {
        String textToSave = generateTextToSave(itemList);

        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToSave);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a string representation of the list of items to be saved to the file.
     *
     * @param itemList The list of items to be converted into a string.
     * @return A string representation of the list of items.
     */
    private static String generateTextToSave(List<Item> itemList) {
        String textToSave = "";

        for (Item item: itemList) {
            if (item != null) {
                textToSave += generateCommandRepresentationOfAnItem(item)
                        + System.lineSeparator();
            }
        }
        return textToSave;
    }

    /**
     * Generates a string representation of a single item in the format of an add command.
     *
     * @param item The item to be converted into a string.
     * @return A string representation of the item in the format of an add command.
     */
    private static String generateCommandRepresentationOfAnItem(Item item) {
        String output = "";

        output += "add" + " "
                + "n/" + item.getItemName() + " "
                + "d/" + item.getItemDescription() + " "
                + "q/" + item.getItemQuantity() + " "
                + "e/" + item.getItemExpirationDate() + " "
                + "s/" + item.getItemSalePrice() + " "
                + "c/" + item.getItemCostPrice();

        return output;
    }
}
