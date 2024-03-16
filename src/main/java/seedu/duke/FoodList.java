package seedu.duke;

import java.util.ArrayList;

public class FoodList {
    private ArrayList<Food> foods;

    public FoodList() {
        this.foods = new ArrayList<>();
    }

    public FoodList(ArrayList<Food> foods) {
        this.foods = foods;
    }
}


