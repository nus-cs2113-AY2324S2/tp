package longah;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

import longah.node.Group;
import longah.exception.LongAhException;
import longah.handler.InputHandler;
import longah.commands.Command;

/**
 * LongAh class manages debts between members.
 */
public class LongAh {
    private static final Logger LongAhLogger = Logger.getLogger("LongAh");
    private static Group group;
    private Scanner scanner;

    /**
     * Constructs a new LongAh instance.
     */
    public LongAh() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * The main method to run the LongAh application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        try {
            FileHandler handler = new FileHandler("./log/LongAh.log");
            handler.setFormatter(new SimpleFormatter());
            LongAhLogger.addHandler(handler);
            LongAhLogger.setUseParentHandlers(false);
        } catch (IOException e) {
            LongAhLogger.log(Level.WARNING, "Log data may not be saved due to permission.");
        }

        LongAhLogger.log(Level.INFO, "Starting Pre-program preparations.");
        System.out.println("Welcome to LongAh!");
        LongAh app = new LongAh();
        try {
            LongAhLogger.log(Level.INFO, "Loading previous member and transaction info.");
            group = new Group();
        } catch (LongAhException e) {
            LongAhLogger.log(Level.WARNING, "Loading process fails! Unable to create file or " +
                    "file could not be access.");
            LongAhException.printException(e);
        }

        LongAhLogger.log(Level.INFO, "Entering main program body. Begin accepting user commands.");
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
                    return;
                }
            } catch (LongAhException e) {
                LongAhLogger.log(Level.WARNING, "The previous user command caused an error. Check the returned " +
                        "error message for details");
                LongAhException.printException(e);
            }
        }
    }
}
