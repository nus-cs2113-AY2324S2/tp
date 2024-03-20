package longah;

import java.util.Scanner;

import longah.node.Group;
import longah.exception.LongAhException;
import longah.handler.InputHandler;
import longah.commands.Command;

/**
 * LongAh class manages debts between members.
 */
public class LongAh {
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
        System.out.println("Welcome to LongAh!");
        LongAh app = new LongAh();
        try {
            group = new Group();
        } catch (LongAhException e) {
            LongAhException.printException(e);
        }

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
                LongAhException.printException(e);
            }
        }
    }
}
