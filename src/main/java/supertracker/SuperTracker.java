package supertracker;

import supertracker.command.ExitCommand;
import supertracker.command.Command;
import supertracker.command.InvalidCommand;
import supertracker.parser.Parser;

import java.util.Scanner;

public class SuperTracker {
    protected static final String LINE = "--------------------------------------------------------------------------\n";
    protected static final String GREET_MESSAGE = LINE + "Hello, welcome to SuperTracker, how may I help you?\n" + LINE;
    protected static final String EXIT_MESSAGE = LINE + "Goodbye!\n" + LINE;

    /**
     * Main entry-point for the java.supertracker.SuperTracker application.
     */
    public static void main(String[] args) {
        System.out.println(GREET_MESSAGE);

        Scanner in = new Scanner(System.in);
        Command command;
        do {
            String input = in.nextLine();
            command = Parser.parseCommand(input.trim());
            System.out.println("\t" + input.trim());
        } while (!command.isExit());

        in.close();
        System.out.println(EXIT_MESSAGE);
    }
}
