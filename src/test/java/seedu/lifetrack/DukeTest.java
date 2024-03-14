package seedu.lifetrack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import seedu.lifetrack.activity.Activity;
import seedu.lifetrack.calorielist.CalorieList;
import seedu.lifetrack.calorielist.Entry;
import seedu.lifetrack.calories.Calorie;

import java.util.ArrayList;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testDeleteCalorie_ValidIndex() {
        CalorieList calorieList = new CalorieList();
        CalorieList.calorieArrayList.add(new Entry(new Activity("2024-03-14","12:00", "Running"),new Calorie(200,true)));
        int initialSize = CalorieList.calorieArrayList.size();
        calorieList.deleteCalorie(1);
        assertEquals(initialSize - 1, CalorieList.calorieArrayList.size());
        CalorieList.calorieArrayList.add(new Entry(new Activity("2024-03-14","12:00", "Running"),new Calorie(200,true)));
        CalorieList.calorieArrayList.add(new Entry(new Activity("2024-03-14","13:00", "Eating"),new Calorie(200,false)));
        initialSize = CalorieList.calorieArrayList.size();
        calorieList.deleteCalorie(2);
        assertEquals(initialSize - 1, CalorieList.calorieArrayList.size());
    }

    @Test
    public void testDeleteCalorie_InvalidIndex() {
        CalorieList calorieList = new CalorieList();
        CalorieList.calorieArrayList.add(new Entry(new Activity("2024-03-14","12:00", "Running"),new Calorie(200,true)));
        int initialSize = CalorieList.calorieArrayList.size();
        calorieList.deleteCalorie(2); // Index out of bounds
        calorieList.deleteCalorie(-1);
        assertEquals(initialSize, CalorieList.calorieArrayList.size());
    }
}
