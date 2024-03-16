package ui;

import java.util.Scanner;

public class UI {
    public static final String SEPARATOR = "-----------------------------";
    private static String name;
    public static void sayHi() {
        System.out.println(SEPARATOR);
        System.out.println("Welcome to Recipe.io!");
        System.out.println("What is your name?");
        Scanner input = new Scanner(System.in);
        name = input.nextLine();
        System.out.println("Hello " + name + "!");
        System.out.println(SEPARATOR);
    }

    public static void printMessage(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    public void helpCommand(){
        //will add content once we finished discussing the commands
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
