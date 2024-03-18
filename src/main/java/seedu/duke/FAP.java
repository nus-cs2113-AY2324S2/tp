package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.modules.ModuleList;
import seedu.duke.parser.Parser;

import java.util.Scanner;

import static seedu.duke.ui.Ui.printGreeting;

public class FAP {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static ModuleList takenModuleList = new ModuleList(10);
    public static ModuleList toBeTakenModuleList = new ModuleList(10);
    public static void main(String[] args) {
        printGreeting();
        while(true) {
            try {
                Scanner in = new Scanner(System.in);
                String userInput = in.nextLine();
                String cleanUserInput = userInput.trim();
                Command command = Parser.getCommand(cleanUserInput);
                command.execute(cleanUserInput);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                break;
            }
        }
    }
}
