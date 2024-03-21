package seedu.lifetrack.calories.calorielist;


import static seedu.lifetrack.system.exceptions.ErrorMessages.printIndexOutOfBoundsError;
import static seedu.lifetrack.system.exceptions.ErrorMessages.printNumberFormatError;
import static seedu.lifetrack.system.parser.ParserCalories.parseCaloriesInput;
import static seedu.lifetrack.ui.CalorieListUi.emptyListMessage;
import static seedu.lifetrack.ui.CalorieListUi.successfulDeletedMessage;
import static seedu.lifetrack.ui.CalorieListUi.printNewCalorieEntry;
import static seedu.lifetrack.ui.CalorieListUi.calorieListHeader;

import seedu.lifetrack.system.exceptions.InvalidInputException;

import java.util.ArrayList;

public class CalorieList {
    
    private ArrayList<Entry> calorieArrayList;
    private final int SIZE_OF_DELETE = 7;

    public CalorieList() {
        calorieArrayList= new ArrayList<>();
    }

    public Entry getEntry(int index) {
        return calorieArrayList.get(index);
    }

    /**
     * Index should be in an integer from 1 to size of the list.
     * @param line the string containing the index of calorie record user want to delete
     */
    public void deleteEntry(String line) {
        try {
            int index = Integer.parseInt(line.substring(SIZE_OF_DELETE).trim());
            Entry toDelete = calorieArrayList.get(index-1);
            calorieArrayList.remove((index-1));  // transfer to scope 0 to size-1
            successfulDeletedMessage(toDelete);
        } catch (IndexOutOfBoundsException e) {
            printIndexOutOfBoundsError();
        } catch ( NumberFormatException e) {
            printNumberFormatError();
        }
    }

    /**
     * Parses a string input representing calorie intake and adds it to the calorie list.
     *
     * This method takes a string input representing calorie intake information and
     * attempts to parse it using the parseCaloriesIn method from the Parser class.
     * If the input format is incorrect or contains missing components, it catches
     * the InvalidInputException and prints an error message. Otherwise, it adds
     * the parsed Entry object to the calorieArrayList.
     *
     * @param input the input string containing date, time, activity, and calorie count
     */
    public void addEntry(String input) {
        try {
            Entry newEntry = parseCaloriesInput(input);
            calorieArrayList.add(newEntry);
            printNewCalorieEntry(newEntry);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the list of calorie entries along with its activity description.
     * If the list is empty, it prints a message indicating that the list is empty.
     * Otherwise, it prints each entry's activity description and calorie count.
     */
    public void printCalorieList() {
        if (calorieArrayList.isEmpty()) {
            emptyListMessage();
        } else {
            calorieListHeader();
            for (int i = 0; i < calorieArrayList.size(); i++) {
                System.out.println("\t " + (i + 1) + ". " + calorieArrayList.get(i).toString());
            }
        }
    }

    public int getSize() {
        return calorieArrayList.size();
    }
}
