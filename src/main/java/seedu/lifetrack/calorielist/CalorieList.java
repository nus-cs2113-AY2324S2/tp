package seedu.lifetrack.calorielist;

import seedu.lifetrack.calories.Calorie;
import seedu.lifetrack.parser.Parser;

import java.util.ArrayList;

public class CalorieList {
    public ArrayList<Entry> calorieArrayList;
    public static ArrayList<Entry> calorieArrayList;

    public CalorieList(){
        calorieArrayList= new ArrayList<>();
    }

    /**
     * Index should be in an integer from 1 to size of the list.
     * @param index the index of calorie record user want to delete
     */
    public void deleteCalorie(int index)
    {
        try{
            if(index>calorieArrayList.size())
            {
                System.out.println("Sorry, this index is out of out of range. Please enter a valid index.");
                return;
            }
            this.calorieArrayList.remove((index-1));  // transfer to scope 0 to size-1
            System.out.println("Successfully delete the calorie record.");
        } catch (IndexOutOfBoundsException e)
        {
            System.out.println("Sorry, this index is invalid. Please enter a positive integer.");
        }
    }

            Entry newEntry = Parser.parseCaloriesIn(input);
            calorieArrayList.add(newEntry);
}
