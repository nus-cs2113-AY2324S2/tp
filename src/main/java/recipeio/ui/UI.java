package recipeio.ui;

import recipeio.recipe.Recipe;

import java.io.InputStream;
import java.util.Scanner;

public class UI {
    public static final String SEPARATOR = "-----------------------------";
    private static String name;

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
        System.out.println(SEPARATOR);
        System.out.println("Woo hoo chef! I have added this recipe to your recipe book:\n\t" + recipe.toString() +
                "\nYou now have " + recipeListSize + " recipes in your recipe book. Keep adding some!");
        System.out.println(SEPARATOR);
    }

    public static void printDeleteMessage(Recipe recipe, int recipeListSize) {
        System.out.println(SEPARATOR);
        System.out.println("Okay chef! I have deleted this recipe from your recipe book:\n\t" +  recipe.toString() +
                "\nYou now have " + recipeListSize + " recipes in your recipe book. Keep adding some!");
        System.out.println(SEPARATOR);
    }

    public void helpCommand(){
        //will add content once we finished discussing the commands
    }

    public static String getName(){
        return name;
    }

    public static void bye() {
        printLine();
        System.out.println("See you again!");
        printLine();
    }

    public String getUserInput() {
        printLine();
        System.out.println("Enter command:");
        String fullInputLine = in.nextLine();

        System.out.println("[Command entered: " + fullInputLine + "]");
        return fullInputLine;
    }

}
