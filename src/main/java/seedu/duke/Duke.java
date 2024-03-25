package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo =
                ".------..------..------..------..------..------..------..------..------..------.\n" +
                        "|S.--. ||P.--. ||L.--. ||I.--. ||T.--. ||L.--. ||I.--. ||A.--. ||N.--. ||G.--. |\n" +
                        "| :/\\: || :/\\: || :/\\: || (\\/) || :/\\: || :/\\: || (\\/) || (\\/) || :(): || :/\\: |\n" +
                        "| :\\/: || (__) || (__) || :\\/: || (__) || (__) || :\\/: || :\\/: || ()() || :\\/: |\n" +
                        "| '--'S|| '--'P|| '--'L|| '--'I|| '--'T|| '--'L|| '--'I|| '--'A|| '--'N|| '--'G|\n" +
                        "`------'`------'`------'`------'`------'`------'`------'`------'`------'`------'\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        Help.printHelp();

        while(in.hasNextLine()) {
            String userInput = in.nextLine();
            Parser parser = new Parser(userInput);

            try {
                parser.handleUserInput();
            } catch (Parser.EndProgramException e) {
                break;
            } catch (ExpensesException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Goodbye!");
    }
}
