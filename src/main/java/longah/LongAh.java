package longah;

import java.util.Scanner;

import longah.node.Group;
import longah.util.Logging;
import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;
import longah.handler.InputHandler;
import longah.commands.Command;

/**
 * LongAh class manages debts between members.
 */
public class LongAh {
    private static final Logging log = new Logging();
    private static Group group;
    private Scanner scanner = new Scanner(System.in);

    /**
     * The main method to run the LongAh application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Logging.logInfo("Starting Pre-program preparations.");
        System.out.println("Welcome to LongAh!");
        LongAh app = new LongAh();
        try {
            group = new Group("group"); // Give a temporary name for now
        } catch (LongAhException e) {
            Logging.logWarning("Loading process fails! Unable to create file or " +
                    "file could not be access.");
            LongAhException.printException(e);
        }

        Logging.logInfo("Entering main program body. Begin accepting user commands.");
        while (true) {
            try {
                System.out.print("Enter command: ");
                if (!app.scanner.hasNextLine()) {
                    return;
                }
                String command = app.scanner.nextLine();
                Command c = InputHandler.parseInput(command);
                c.execute(group);

                // Check will not be reached if exception is thrown
                if (c.isExit()) {
                    System.exit(0);
                }
            } catch (LongAhException e) {
                LongAhException.printException(e);
                // Log critical errors
                if (e.getMessage().equals(ExceptionMessage.TRANSACTIONS_SUMMED_UP.getMessage()) ||
                        e.getMessage().equals(ExceptionMessage.NO_DEBTS_FOUND.getMessage()) || 
                        e.getMessage().equals(ExceptionMessage.NO_TRANSACTION_FOUND.getMessage())) {
                    Logging.logWarning("The previous user command caused an error. " +
                            "Check the returned error message for details");
                }
            }
        }
    }
}
