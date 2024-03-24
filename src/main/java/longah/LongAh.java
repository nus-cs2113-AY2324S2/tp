package longah;

import longah.handler.PINHandler;
import longah.handler.StorageHandler;
import longah.node.Group;
import longah.exception.ExceptionMessage;
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
    private static PINHandler pinHandler;
    private static final Logging logger = new Logging();

    public static void init() {
        Logging.logInfo("Starting Pre-program preparations.");
        UI.showMessage("Welcome to LongAh!");
        StorageHandler.initDir();
    }

    /**
     * The main method to run the LongAh application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        init();
        try {
            group = new Group("group"); // Give a temporary name for now
            pinHandler = new PINHandler();
        } catch (LongAhException e) {
            Logging.logWarning("Loading process fails! Unable to create file or " +
                    "file could not be access.");
            LongAhException.printException(e);
        }

        Logging.logInfo("Entering main program body. Begin accepting user commands.");
        while (true) {
            try {
                if (!UI.hasNextLine()) {
                    System.exit(0);
                }
                System.out.print("Enter command:");
                String command = UI.getUserInput();
                Command c = InputHandler.parseInput(command);
                c.execute(group);

                // Check will not be reached if exception is thrown
                if (c.isExit()) {
                    System.exit(0);
                }
            } catch (LongAhException e) {
                LongAhException.printException(e);
                // Log only critical errors
                if (LongAhException.isMessage(e, ExceptionMessage.TRANSACTIONS_SUMMED_UP) ||
                        LongAhException.isMessage(e, ExceptionMessage.NO_DEBTS_FOUND) ||
                        LongAhException.isMessage(e, ExceptionMessage.NO_TRANSACTION_FOUND)) {
                    Logging.logWarning("The previous user command caused an error. " +
                            "Check the returned error message for details");
                }
            }
        }
    }
}
