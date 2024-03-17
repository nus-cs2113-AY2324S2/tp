package supertracker;

import supertracker.command.Command;
import supertracker.command.InvalidCommand;
import supertracker.command.QuitCommand;
import supertracker.parser.Parser;
import supertracker.ui.Ui;

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
            try {
                command = Parser.parseCommand(input.trim());
                command.execute();
            } catch (TrackerException e) {
                Ui.printError(e);
                command = new InvalidCommand();
            }
            Ui.printLine();
        } while (!command.isQuit());
        
        assert command instanceof QuitCommand;
        in.close();
    }
}
