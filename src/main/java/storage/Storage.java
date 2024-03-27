package storage;

import command.Command;
import exceptions.CommandFormatException;
import item.Item;
import itemlist.Itemlist;
import parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents an add command where a new task is added to the existing list of task.
 * String <code>FILENAME</code> represents the designated relative file path for the file.
 * File <code>dukeData</code> represents the File object created to be updated.
 */
public class Storage {
    private static final String FILENAME = "./StockMasterData.txt";

    /**
     * Write contents to the file.
     *
     * @param filePath  File path where the file is located.
     * @param textToAdd The line of text to write to the file.
     * @param ifAppend  Indicate if append the text at the end of the file (true)
     *                  or overwrite the file (false).
     * @throws IOException If file is not found at the indicated file path.
     */
    public static void writeToFile(String filePath, String textToAdd, boolean ifAppend) throws IOException {
        FileWriter writer = new FileWriter(filePath, ifAppend);
        writer.write(textToAdd);
        writer.close();
    }

    public static void updateFile(String inputText, boolean ifAppend) {
        try {
            writeToFile(getFileDirectory(), inputText, ifAppend);
        } catch (IOException e) {
            System.out.println("IOExceptions occurred");
        }
    }

    /**
     * Returns the private file directory of storage.
     */
    public static String getFileDirectory() {
        return FILENAME;
    }

    /**
     * Set the private File dukeData.
     */
    public static File setFile() {
        return new File(FILENAME);
    }

    /**
     * Read lines from the file and identify tasks written inside.
     * Add the identified tasks into a list of existing tasks.
     *
     */
    public static void readFromFile(String fileName) {
        Parser parser = new Parser();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                String fileLine = "add" + scanner.nextLine();
                String[] keyCommands = fileLine.split(" \\| ");
                String commandQty = "";
                String commandCat = "";
                String commandUom = "";
                String commandName = "";
                for (String keyCommand : keyCommands) {
                    if (keyCommand.contains(".")) {
                        //do nothing.
                    }
                    else if (keyCommand.contains("Qty: ")) {
                        String[] commandQtyUnit = keyCommand.replace("Qty: ", "").split(" ");
                        assert commandQtyUnit.length == 2 : "length not 2!";
                        commandQty = commandQtyUnit[0];
                        commandUom = commandQtyUnit[1];
                    }
                    else if (keyCommand.contains("Cat: ")) {
                        commandCat = keyCommand.replace("Cat: ", "");
                    }
                    else {
                        commandName = keyCommand;
                    }
                }
                Item toAdd = new Item(commandName, Integer.parseInt(commandQty), commandUom, commandCat);
                Itemlist.addItem(toAdd);
            }
        } catch(FileNotFoundException e){
            System.out.println("File does not exist.");
        }
    }

    public static void addToFile(ArrayList<Item> items, boolean ifAppend) {
        assert items != null : "Items cannot be null.";
        Item lastItem = items.get(items.size() - 1);
        String descriptionAdded = (items.size()) + "." + " | " + lastItem.getItemName() +
                " | " + "Qty: " + lastItem.getQuantity() + " " + lastItem.getUom() + " | " + "Cat: " +
                lastItem.getCategory() + "\n";
        updateFile(descriptionAdded, ifAppend);
    }

    public static void overwriteFile(ArrayList<Item> items, boolean ifAppend) {
        assert items != null : "Items cannot be null.";
        int length = items.size();
        for (int index = 0; index < length; index++) {
            String descriptionAdded = (index + 1) + "." + " | " + items.get(index).getItemName() +
                    " | " + "Qty: " + items.get(index).getQuantity() + " " + items.get(index).getUom() +
                    " | " + "Cat: " + items.get(index).getCategory() + "\n";
            if (index == 0) {
                updateFile(descriptionAdded, ifAppend);
            } else {
                updateFile(descriptionAdded, !ifAppend);
            }
        }
    }
}
