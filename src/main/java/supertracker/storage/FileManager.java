package supertracker.storage;

import supertracker.item.Inventory;
import supertracker.item.Item;
import supertracker.ui.ErrorMessage;
import supertracker.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    protected static final String DATA_PATH = "./data/";
    protected static final String FILE_NAME = "items.txt";
    protected static final String SAVE_FILE_PATH = DATA_PATH + FILE_NAME;
    protected static final String SEPARATOR = ",,,";
    protected static final String PLACEHOLDER = "*&_";
    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    protected static final String NO_DATE = "no date";
    protected static final DateTimeFormatter DATE_FORMAT_NULL = DateTimeFormatter.ofPattern("dd-MM-yyyyy");
    protected static final LocalDate DATE_NOT_EXIST = LocalDate.parse("01-01-99999", DATE_FORMAT_NULL);
    protected static final int MAX_NUMBER_OF_PARAMS = 5;
    protected static final int NAME_INDEX = 0;
    protected static final int QUANTITY_INDEX = 1;
    protected static final int PRICE_INDEX = 2;
    protected static final int DATE_INDEX = 3;
    protected static final int EXTRA_INDEX = 4;

    /**
     * Saves all items currently in the inventory by writing into a text file.
     *
     * @throws IOException if text file cannot be opened or accessed for whatever reason
     */
    public static void saveData() throws IOException {
        File directory = new File(DATA_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File saveFile = new File(SAVE_FILE_PATH);
        if (!saveFile.createNewFile()) {
            saveFile.delete();
            saveFile.createNewFile();
        }

        List<Item> items = Inventory.getItems();
        FileWriter fw = new FileWriter(saveFile);
        BufferedWriter writer = new BufferedWriter(fw);

        for (Item item : items) {
            String itemData = getItemData(item);
            writer.write(itemData);
        }

        writer.close();
        fw.close();
    }

    /**
     * Loads and reads data from a designated text file from the path specified in the class.
     * Parses each line of data into an Item class and adds to the item list in the Inventory class.
     * If data is corrupted, prints to the UI the number of corrupted lines.
     *
     * @throws IOException if specified path is unable to be opened or found
     */
    public static void loadData() throws IOException {
        File directory = new File(DATA_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
            return;
        }

        File saveFile = new File(SAVE_FILE_PATH);
        if (!saveFile.exists()) {
            return;
        }

        Inventory.clear();
        Scanner s = new Scanner(saveFile);
        String itemData;
        int corruptedData = 0;
        while (s.hasNext()) {
            try {
                itemData = s.nextLine();
                Item item = parseItemData(itemData);
                Inventory.put(item.getName(), item);
            } catch (Exception e) {
                corruptedData++;
            }
        }
        if (corruptedData != 0) {
            Ui.printError(ErrorMessage.FILE_CORRUPTED_ERROR);
        }
        s.close();
    }

    private static String getItemData(Item item) {
        String name = item.getName();
        String excess = "";
        if (name.contains(SEPARATOR)) {
            excess = "e";
            name = name.replace(SEPARATOR, PLACEHOLDER);
        }

        String quantity = String.valueOf(item.getQuantity());
        String price = String.valueOf(item.getPrice());

        LocalDate exDate = item.getExpiryDate();
        String date = NO_DATE;
        if (!exDate.isEqual(DATE_NOT_EXIST)) {
            date = exDate.format(DATE_FORMAT);
        }

        return name + SEPARATOR + quantity + SEPARATOR + price + SEPARATOR + date + SEPARATOR + excess + "\n";
    }

    private static Item parseItemData(String itemData) throws Exception {
        String[] data = itemData.split(SEPARATOR, MAX_NUMBER_OF_PARAMS);

        if (data.length < MAX_NUMBER_OF_PARAMS) {
            throw new Exception();
        }
        assert data.length == MAX_NUMBER_OF_PARAMS;

        String name = data[NAME_INDEX].trim();
        if (data[EXTRA_INDEX].equals("e")) {
            name = name.replace(PLACEHOLDER, SEPARATOR);
        }

        int quantity;
        double price;
        quantity = Integer.parseInt(data[QUANTITY_INDEX].trim());
        price = Double.parseDouble(data[PRICE_INDEX].trim());
        if (quantity < 0 && price < 0) {
            throw new Exception();
        }

        LocalDate date = DATE_NOT_EXIST;
        if (!data[DATE_INDEX].equals(NO_DATE)) {
            date = LocalDate.parse(data[DATE_INDEX], DATE_FORMAT);
        }

        return new Item(name, quantity, price, date);
    }
}
