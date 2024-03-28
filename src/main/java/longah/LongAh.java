package longah;

import longah.handler.PINHandler;
import longah.node.Group;
import longah.exception.LongAhException;
import longah.handler.InputHandler;
import longah.handler.Logging;
import longah.handler.UI;
import longah.commands.Command;

/**
 * LongAh class manages debts between members.
 */
public class LongAh {
    private static Group group;

    public static void init() {
        new Logging();
        UI.showMessage("Welcome to LongAh!");
    }

    /**
     * The main method to run the LongAh application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        init();

        Logging.logInfo("Starting Pre-program preparations.");
        try {
            group = new Group("group"); // Give a temporary name for now
            new PINHandler();
        } catch (LongAhException e) {
            LongAhException.printException(e);
        }

        Logging.logInfo("Entering main program body. Begin accepting user commands.");
        while (true) {
            try {
                UI.showCommandPrompt();
                String command = UI.getUserInput();
                if (command == null) {
                    continue;
                }
                Command c = InputHandler.parseInput(command);
                c.execute(group);

                // Check will not be reached if exception is thrown
                if (c.isExit()) {
                    System.exit(0);
                }
            } catch (LongAhException e) {
                LongAhException.printException(e);
            }
        }
    }
}
