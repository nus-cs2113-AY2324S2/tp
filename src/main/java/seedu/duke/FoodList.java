package seedu.duke;

import java.util.ArrayList;
import java.util.Random;

public class FoodList {
    private ArrayList<Food> foods;

    public FoodList() {
        this.foods = new ArrayList<>();
    }

    public FoodList(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public Food getRandomFood() {
        Random random = new Random();
        int foodIndex = random.nextInt(9);
        return foods.get(foodIndex);
    }

    public Food getFilteredFood(String preferredLocation, String preferredPrice) {
        ArrayList<Food> filteredFoods = new ArrayList<>();
        for (Food eachFood : foods) {
            if (eachFood.location.equals(preferredLocation) && eachFood.price.equals(preferredPrice)) {
                filteredFoods.add(eachFood);
            }
        }
        
        Random random = new Random();
        int filteredFoodIndex = random.nextInt(filteredFoods.size());
        return filteredFoods.get(filteredFoodIndex);
    }
}


