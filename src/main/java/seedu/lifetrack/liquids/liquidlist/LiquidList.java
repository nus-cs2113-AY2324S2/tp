package seedu.lifetrack.liquids.liquidlist;

import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.liquids.Beverage;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.parser.ParserLiquid;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.lifetrack.ui.LiquidListUI.deleteLogIndexMessage;
import static seedu.lifetrack.ui.LiquidListUI.deleteLogNumberMessage;
import static seedu.lifetrack.ui.LiquidListUI.deleteMessage;
import static seedu.lifetrack.ui.LiquidListUI.addEntryMessage;
import static seedu.lifetrack.ui.LiquidListUI.emptyListMessage;
import static seedu.lifetrack.ui.LiquidListUI.WHITESPACE;
import static seedu.lifetrack.ui.LiquidListUI.listHeader;

/**
 * Represents a list of liquid entries.
 * Provides methods to add, delete, and print liquid entries.
 */
public class LiquidList {
    private static Logger logr = Logger.getLogger(CalorieList.class.getName());
    private ArrayList<LiquidEntry> liquidArrayList;
    private final int DELETE_PADDING = 15;

    /**
     * Constructs an empty LiquidList.
     */
    public LiquidList() {
        liquidArrayList = new ArrayList<>();
    }

    /**
     * Retrieves the liquid entry at the specified index.
     *
     * @param index the index of the liquid entry to retrieve
     * @return the liquid entry at the specified index
     */
    public LiquidEntry getEntry(int index) {
        assert index >= 0 && index < liquidArrayList.size() : "Index out of bounds";
        return liquidArrayList.get(index);
    }

    /**
     * Deletes the liquid entry indicated by the provided line.
     *
     * @param line the string containing the index of the liquid record to delete
     */
    public void deleteEntry(String line) {
        try {
            int index = Integer.parseInt(line.substring(DELETE_PADDING).trim());
            liquidArrayList.remove(index - 1);
            deleteMessage();
        } catch (IndexOutOfBoundsException e) {
            logr.log(Level.WARNING, deleteLogIndexMessage(), e);
        } catch (NumberFormatException e) {
            logr.log(Level.WARNING, deleteLogNumberMessage(), e);
        }
    }

    /**
     * Adds a new liquid entry based on the provided input.
     *
     * @param input the input string containing liquid entry information
     */
    public void addEntry(String input) {
        try {
            LiquidEntry newEntry = ParserLiquid.parseLiquidInput(input);
            liquidArrayList.add(newEntry);
            addEntryMessage();
        } catch (InvalidInputException e) {
            logr.log(Level.WARNING, e.getMessage(), e);
        }
    }

    /**
     * Prints the list of liquid entries.
     * If the list is empty, prints a message indicating that the list is empty.
     */
    public void printLiquidList() {
        if (liquidArrayList.isEmpty()) {
            emptyListMessage();
        } else {
            listHeader();
            for (int i = 0; i < liquidArrayList.size(); i++) {
                LiquidEntry entry = liquidArrayList.get(i);
                Beverage beverage = entry.getBeverage();
                System.out.println(WHITESPACE + (i + 1) + ". Beverage: " + beverage.getBeverage()
                        + ", Volume: " + beverage.getVolume());
            }
        }
    }

    /**
     * Retrieves the size of the liquid list.
     *
     * @return the number of liquid entries in the list
     */
    public int getSize() {
        return liquidArrayList.size();
    }
}
