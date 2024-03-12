package supertracker;

import supertracker.command.Command;
import supertracker.parser.Parser;

import java.util.Scanner;

public class SuperTracker {
    /**
     * Main entry-point for the java.supertracker.SuperTracker application.
     */
    public static void main(String[] args) {
        run();
    }

    /**
     * Runs the java.supertracker.SuperTracker application.
     */
    private static void run() {
        Ui.greetUser();
        handleCommands();
    }

    private static void handleCommands() {
        Scanner in = new Scanner(System.in);
        Command command;
        do {
            String input = in.nextLine();
            Ui.printLine();
            command = Parser.parseCommand(input.trim());
            command.execute();
            Ui.printLine();
        } while (!command.isQuit());
        in.close();
    }
}
