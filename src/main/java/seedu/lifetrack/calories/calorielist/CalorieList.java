package seedu.lifetrack.calories.calorielist;

import seedu.lifetrack.system.Parser;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import java.util.ArrayList;

public class CalorieList {
    
    private ArrayList<Entry> calorieArrayList;
    private final int DELETE_PADDING = 16;

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
            int index = Integer.parseInt(line.substring(DELETE_PADDING).trim());
            calorieArrayList.remove((index-1));  // transfer to scope 0 to size-1
            System.out.println("Successfully delete the calorie record.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, this index is invalid. Please enter a positive integer " +
                    "within the size of the list.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid index!");
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
            Entry newEntry = Parser.parseCaloriesInput(input);
            calorieArrayList.add(newEntry);
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
