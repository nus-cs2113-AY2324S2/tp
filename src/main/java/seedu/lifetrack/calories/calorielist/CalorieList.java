package seedu.lifetrack.calories.calorielist;

import seedu.lifetrack.Entry;
import seedu.lifetrack.system.exceptions.ErrorMessages;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.parser.ParserCalories;
import seedu.lifetrack.system.storage.FileHandler;
import seedu.lifetrack.ui.CalorieListUi;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CalorieList {

    private static Logger logr = Logger.getLogger(CalorieList.class.getName());
    
    private final int SIZE_OF_DELETE = 16;
    private ArrayList<Entry> calorieArrayList;
    private FileHandler fileHandler;

    //constructor for JUnit tests
    public CalorieList() {
        calorieArrayList = new ArrayList<>();
    }

    //constructor for usage in terminal
    public CalorieList(String filePath) {
        try {
            fileHandler = new FileHandler(filePath);
            calorieArrayList = fileHandler.getCalorieEntriesFromFile();
        } catch (FileNotFoundException e) {
            calorieArrayList = new ArrayList<>();
            System.out.println(ErrorMessages.getFileNotFoundMessage());
        }
    }

    private void updateFile() {
        if (fileHandler != null) {
            fileHandler.writeEntries(calorieArrayList);
        }
    }

    public Entry getEntry(int index) {
        return calorieArrayList.get(index);
    }

    /**
     * Index should be in an integer from 1 to size of the list.
     * @param line the string containing the index of calorie record user want to delete
     */
    public void deleteEntry(String line) {
        assert (line.startsWith("calories delete") ) : "ensures that input is correct";

        try {
            int index = Integer.parseInt(line.substring(SIZE_OF_DELETE).trim());
            Entry toDelete = calorieArrayList.get(index-1);
            calorieArrayList.remove((index-1));  // transfer to scope 0 to size-1
            updateFile();
            CalorieListUi.successfulDeletedMessage(toDelete);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(CalorieListUi.deleteLogIndexMessage());
        } catch (NumberFormatException e) {
            System.out.println(CalorieListUi.deleteLogNumberMessage());
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
        assert (input.startsWith("calories in") || input.startsWith("calories out")) : "ensures that input is correct";
        logr.setLevel(Level.WARNING);
        try {
            Entry newEntry = ParserCalories.parseCaloriesInput(input);
            calorieArrayList.add(newEntry);
            updateFile();
            CalorieListUi.printNewCalorieEntry(newEntry);
        } catch (InvalidInputException e) {
            logr.log(Level.WARNING, e.getMessage(), e);
        }
    }

    /**
     * Prints the list of calorie entries along with its activity description.
     * If the list is empty, it prints a message indicating that the list is empty.
     * Otherwise, it prints each entry's activity description and calorie count.
     */
    public void printCalorieList() {
        if (calorieArrayList.isEmpty()) {
            CalorieListUi.emptyListMessage();
        } else {
            CalorieListUi.calorieListHeader();
            for (int i = 0; i < calorieArrayList.size(); i++) {
                Entry entry = calorieArrayList.get(i);
                System.out.println("\t " + (i + 1) + ". " + entry);
            }
        }
    }

    public int getSize() {
        return calorieArrayList.size();
    }
}
