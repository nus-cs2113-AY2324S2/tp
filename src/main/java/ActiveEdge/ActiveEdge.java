package ActiveEdge;

import Command.ActiveEdgeException;
import ActiveEdge.Parser.Parser;
import ActiveEdge.Ui.ByeUi;

import java.util.Scanner;

public class ActiveEdge {
    /**
     * Main entry-point for the ActiveEdge application.
     */
    public static void run() {
        Scanner in = new Scanner(System.in);

        String logo = "ACTIVE EDGE";
        System.out.println("Hello from\n" + logo + " AI assistant!");
        System.out.println("How can I help you today?");

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
