package seedu.lifetrack.calories.calorielist;

import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.parser.ParserCalories;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class CalorieList {
    
    private ArrayList<Entry> calorieArrayList;
    private final int DELETEPADDING = 16;
    private static Logger logr = Logger.getLogger(CalorieList.class.getName());


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
            int index = Integer.parseInt(line.substring(DELETEPADDING).trim());
            calorieArrayList.remove((index-1));  // transfer to scope 0 to size-1
            System.out.println("Successfully delete the calorie record.");
        } catch (IndexOutOfBoundsException e) {
            logr.log(Level.WARNING, "Sorry, this index is invalid. Please enter a positive integer " +
                    "within the size of the list.", e);
        } catch (NumberFormatException e) {
            logr.log(Level.WARNING, "Please enter a valid index!", e);
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
            Entry newEntry = ParserCalories.parseCaloriesInput(input);
            calorieArrayList.add(newEntry);
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
            System.out.println("Your caloric list is empty.");
        } else {
            System.out.println("Caloric List:");
            for (int i = 0; i < calorieArrayList.size(); i++) {
                Entry entry = calorieArrayList.get(i);
                System.out.println((i + 1) + ". " + entry);
            }
        }
    }

    public int getSize() {
        return calorieArrayList.size();
    }
}
