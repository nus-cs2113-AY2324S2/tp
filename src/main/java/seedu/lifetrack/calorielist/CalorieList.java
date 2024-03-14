package seedu.lifetrack.calorielist;

import seedu.lifetrack.parser.Parser;
import seedu.lifetrack.exceptions.InvalidInputException;
import java.util.ArrayList;

public class CalorieList {
    
    public ArrayList<Entry> calorieArrayList;

    public CalorieList() {
        calorieArrayList= new ArrayList<>();
    }

    /**
     * Index should be in an integer from 1 to size of the list.
     * @param index the index of calorie record user want to delete
     */
    public void deleteEntry(int index) {
        try {
            if(index > calorieArrayList.size()) {
                System.out.println("Sorry, this index is out of out of range. Please enter a valid index.");
                return;
            }
            calorieArrayList.remove((index-1));  // transfer to scope 0 to size-1
            System.out.println("Successfully delete the calorie record.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, this index is invalid. Please enter a positive integer.");
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
            Entry newEntry = Parser.parseCaloriesIn(input);
            calorieArrayList.add(newEntry);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
