package recipeio.ui;

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
