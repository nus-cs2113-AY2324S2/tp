package seedu.lifetrack.liquids.liquidlist;

import seedu.lifetrack.liquids.Beverage;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.parser.ParserLiquid;

import java.util.ArrayList;

/**
 * Represents a list of liquid entries.
 * Provides methods to add, delete, and print liquid entries.
 */
public class LiquidList {

    private ArrayList<LiquidEntry> liquidArrayList;
    private final int SIZE_OF_DELETE = 15;

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
            int index = Integer.parseInt(line.substring(SIZE_OF_DELETE).trim());
            liquidArrayList.remove(index - 1);
            System.out.println("Successfully delete the liquid record.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, this index is invalid. Please enter a positive integer " +
                    "within the size of the list.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid index!");
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
            System.out.println("Beverage has been successfully added");
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the list of liquid entries.
     * If the list is empty, prints a message indicating that the list is empty.
     */
    public void printLiquidList() {
        if (liquidArrayList.isEmpty()) {
            System.out.println("Your liquid list is empty.");
        } else {
            System.out.println("Liquid List:");
            for (int i = 0; i < liquidArrayList.size(); i++) {
                LiquidEntry entry = liquidArrayList.get(i);
                Beverage beverage = entry.getBeverage();
                System.out.println((i + 1) + ". Beverage: " + beverage.getBeverage()
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
