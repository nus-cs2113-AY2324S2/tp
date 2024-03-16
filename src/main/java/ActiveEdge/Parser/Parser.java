package ActiveEdge.Parser;

import ActiveEdge.FoodStorage;

import java.util.Scanner;

public class Parser {
    public void handleInput(String input, FoodStorage foodStorage, Scanner in) {
        if (input.equalsIgnoreCase("list foods")) {
            foodStorage.printAllFoods();
        } else if (foodStorage.containsFood(input)) {
            int calories = foodStorage.getCalories(input);
            System.out.println(input + " contains " + calories + " calories.");
        } else {
            System.out.println("Sorry, I don't have information about " + input);
        }
    }
}

