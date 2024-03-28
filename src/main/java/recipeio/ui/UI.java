package recipeio.ui;

import recipeio.recipe.Recipe;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public static final String SEPARATOR = "-----------------------------";

    private final Scanner in;

    public UI() {
        this(System.in);
    }

    public UI (InputStream in) {
        this.in = new Scanner(in);
    }

    public static void printLine() {
        System.out.println(SEPARATOR);
    }

    public static void sayHi() {
        printLine();
        System.out.println("Welcome to Recipe.io!");
    }

    public static void printMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public static void printAddMessage(Recipe recipe, int recipeListSize) {
        System.out.println("Woo hoo chef! I have added this recipe to your recipe book:\n\t" + recipe.toString() +
                "\nYou now have " + recipeListSize + " recipes in your recipe book. Keep adding some!");
    }

    public static void printDeleteMessage(Recipe recipe, int recipeListSize) {
        System.out.println("Okay chef! I have deleted this recipe from your recipe book:\n\t" +  recipe.toString() +
                "\nYou now have " + recipeListSize + " recipes in your recipe book. Keep adding some!");
    }

    /**
     * Prints list of accepted instructions.
     */
    public static void printInstructions(){
        System.out.println("Accepted commands are:");
        System.out.println("help: shows available commands\n");
        System.out.println("list: shows you list of recipes");
        System.out.println("\tInput Example: list\n");
        System.out.println("delete LIST_NUMBER: deletes a recipe at a given index");
        System.out.println("\tInput Example: delete 1\n");
        System.out.println("find kw KEYWORD: finds recipes with a given keyword");
        System.out.println("\tInput Example: find kw pizza\n");
        System.out.println("find date YYYY-MM-DD: finds recipes added on a given date");
        System.out.println("\tInput Example: find date 2024-03-28\n");
        System.out.println("add NAME/MINUTES/KCALS/ALLERGIES/CATEGORY/URL: adds a recipe");
        System.out.println("\tInput Example: add pizza/34/340/eggs/dinner/www.food.com\n");
        System.out.println("exit: to leave the program");
    }

    public static void printMatches(ArrayList<Recipe> matches) {
        int counter = 0;
        StringBuilder output = new StringBuilder();
        while (counter < matches.size()) {
            Recipe selected = matches.get(counter);
            output.append(selected.toString());
            if (counter != matches.size() - 1) {
                output.append("\n");
            }
            counter += 1;
        }
        System.out.println("Here are your matches:\n" + output);
    }

    /**
     * Prints warning when an unrecognised command is entered.
     */
    public static void printInvalidCommandWarning() {
        System.out.println("Invalid command.");
    }

    public static void bye() {
        System.out.println("See you again chef! (｡˃ ᵕ ˂ ) ♨");
        printLine();
    }

    /**
     * Asks user for input in console.
     */
    public String getUserInput() {
        printLine();
        System.out.println("Enter command:");
        String fullInputLine = in.nextLine();

        System.out.println("[Command entered: " + fullInputLine + "]");
        printLine();
        return fullInputLine;
    }
}
