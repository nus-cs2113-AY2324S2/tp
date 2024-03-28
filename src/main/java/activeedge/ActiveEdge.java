package activeedge;

import activeedge.ui.CommandUi;
import command.ActiveEdgeException;
import activeedge.parser.Parser;
import activeedge.ui.ByeUi;

import java.util.Scanner;

public class ActiveEdge {
    /**
     * Main entry-point for the ActiveEdge application.
     */
    public static void run() {
        Scanner in = new Scanner(System.in);

        CommandUi.printWelcomeMessage();
        Parser parser = new Parser();

        Storage.fetchData();
        String input = in.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            parser.handleInput(input);
            input = in.nextLine();
        }

        ByeUi.printByeMessage();
    }

    public static void main(String[] args) throws ActiveEdgeException {
        new ActiveEdge().run();
    }

}
