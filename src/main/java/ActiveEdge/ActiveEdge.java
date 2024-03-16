package ActiveEdge;

import ActiveEdge.Parser.Parser;
import ActiveEdge.Ui.ByeUi;

import java.util.Scanner;

public class ActiveEdge {
    /**
     * Main entry-point for the ActiveEdge application.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "ACTIVE EDGE";
        System.out.println("Hello from\n" + logo + " AI assistant!");
        System.out.println("How can I help you today?");

        FoodStorage foodStorage = new FoodStorage();
        Parser parser = new Parser();

        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            parser.handleInput(input, foodStorage, scanner);
            input = scanner.nextLine();
        }

        ByeUi.printByeMessage();
    }
}
