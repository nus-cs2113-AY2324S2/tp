package ActiveEdge;

import java.util.ArrayList;
import java.util.List;

public class FoodStorage {
    private List<FoodItem> foodItems;

    public FoodStorage() {
        foodItems = new ArrayList<>();
        initializeDatabase();
    }

    private void initializeDatabase() {
        // Initialize the database with predefined food items and their calorie information
        addFood("Apple", 95);
        addFood("Banana", 105);
        addFood("Orange", 62);
        // Add more predefined food items as needed
    }

    private void addFood(String foodName, int calories) {
        foodItems.add(new FoodItem(foodName, calories));
    }

    public boolean containsFood(String foodName) {
        for (FoodItem item : foodItems) {
            if (item.getName().equalsIgnoreCase(foodName)) {
                return true;
            }
        }
        return false;
    }

    public int getCalories(String foodName) {
        for (FoodItem item : foodItems) {
            if (item.getName().equalsIgnoreCase(foodName)) {
                return item.getCalories();
            }
        }
        return 0; // Return 0 if the food item is not found
    }

    public void printAllFoods() {
        System.out.println("List of Foods:");
        for (FoodItem item : foodItems) {
            System.out.println(item.getName() + " - " + item.getCalories() + " calories");
        }
    }
}
