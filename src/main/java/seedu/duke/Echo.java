package seedu.duke;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Echo {

    private static ArrayList<String> inputs;

    public static void initializeInputs() {
        inputs = new ArrayList<>();
    }

    public static String echoInput(String input) {
        return input;
    }

    public static void startEcho() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text to echo back, or type 'exit' to quit:");

        try {
            while (true) {
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }
                inputs.add(input);
                echoBack();
                inputs.remove(input);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error caught:" + e.getMessage());
        }
    }

    private static void echoBack() {
        for (String input : inputs ) {
            System.out.println(input);
        }
    }

    public static void main(String[] args) {
        Echo.initializeInputs();
        startEcho();
    }
}
