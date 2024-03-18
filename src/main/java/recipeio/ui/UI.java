package recipeio.ui;

import recipeio.recipe.Recipe;

import java.util.Scanner;

public class UI {
    public static final String SEPARATOR = "-----------------------------";
    private static String name;
    public static void sayHi(Scanner inputGetter) {
        System.out.println(SEPARATOR);
        System.out.println("Welcome to Recipe.io!");
        System.out.println("What is your name?");
        name = inputGetter.nextLine();
        System.out.println("Hello Chef " + name + "! How can I help you today?");
        System.out.println(SEPARATOR);
    }

    public static void printMessage(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    public static void addRecipePrinter(Recipe recipe, int recipeListSize) {
        System.out.println(SEPARATOR);
        System.out.println("Woo hoo chef! I have added this recipe to your recipe book:\n\t" + recipe.toString() +
                "\nYou now have " + recipeListSize + " recipes in your recipe book. Keep adding some!");
        System.out.println(SEPARATOR);
    }

    public static void deleteRecipePrinter(Recipe recipe, int recipeListSize) {
        System.out.println(SEPARATOR);
        System.out.println("Okay chef! I have deleted this recipe from your recipe book:\n\t" +  recipe.toString() +
                "\nYou now have " + recipeListSize + " recipes in your recipe book. Keep adding some!");
        System.out.println(SEPARATOR);
    }

    public static void printHelpCommand(){
        System.out.println(SEPARATOR);
        System.out.println("Sorry, I don't understand what you are saying. Here are your possible commands.\nPlease " +
                "follow the format exactly as shown:\n" +
                "To add a recipe: add [RECIPE NAME] / [COOK TIME IN MINS] / [CALORIES] / " +
                "[ALLERGIES SEPARATED BY COMMAS] / " +
                "[BREAKFAST, LUNCH, DINNER, APPETIZER, OR DESSERT] / [A URL TO THE RECIPE]\nFor example:\n" +
                "add Pizza Bagels / 40 / 540 / eggs, gluten, dairy / lunch / www.thisispizzabagels.com");
        System.out.println(SEPARATOR);
    }
    public static String getName(){
        return name;
    }

    public static void bye() {
        System.out.println(SEPARATOR);
        System.out.println("See you again, " + name + "!");
        System.out.println(SEPARATOR);
    }
}
