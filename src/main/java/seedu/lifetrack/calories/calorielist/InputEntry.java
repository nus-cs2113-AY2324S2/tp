package seedu.lifetrack.calories.calorielist;

import seedu.lifetrack.calories.Food;

public class InputEntry extends Entry {
    
    private Food food;

    public InputEntry(String description, int calories, String date) {
        super(description, calories, date);
    }

    public InputEntry(String description, int calories, String date, Food food) {
        super(description, calories, date);
        this.food = food;
    }
}
