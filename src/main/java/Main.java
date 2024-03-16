import ui.Ui;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isExit = false;

    public static void main(String[] args) {

        // TODO: instantiate objects of each different feature

        while (!isExit) {

            Ui.promptUserInput();

            String userInput = scanner.nextLine();

            //execute user command if it is valid else throw exception
            //save tasks to file after each command
            try {
                // TODO: to add individual objects for each feature in the determineCommand method
                //  input parameters
                // TODO: Execute user command
            } catch (Error e) {
                // TODO: Handle any input errors
            }
        }
    }
}
