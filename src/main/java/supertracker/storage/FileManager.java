package supertracker.storage;

import supertracker.item.Inventory;
import supertracker.item.Item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileManager {
    protected static final String DATA_PATH = "./data/";
    protected static final String FILE_NAME = "items.txt";
    protected static final String SAVE_FILE_PATH = DATA_PATH + FILE_NAME;
    protected static final String SEPARATOR = ",,,";
    protected static final String PLACEHOLDER = "*&_";

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

        List<Item> items = Inventory.items();
        FileWriter fw = new FileWriter(saveFile);
        BufferedWriter writer = new BufferedWriter(fw);

        for (Item item : items) {
            String itemData = getItemData(item);
            writer.write(itemData);
        }

        writer.close();
        fw.close();
    }

    private static String getItemData(Item item) {
        String name = item.getName();
        String excess = "";
        if (name.contains(SEPARATOR)) {
            excess = SEPARATOR + "e";
            name = name.replace(SEPARATOR, PLACEHOLDER);
        }
        String quantity = String.valueOf(item.getQuantity());
        String price = String.valueOf(item.getPrice());

        return name + SEPARATOR + quantity + SEPARATOR + price + excess + "\n";
    }
}
