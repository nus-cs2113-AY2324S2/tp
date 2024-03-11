package seedu.duke;
import java.util.Scanner;

import static ui.Handler.initialiseBot;
import static ui.Handler.processInput;
import static ui.Handler.terminateBot;

public class Duke {
    /**
     * Main entry-point for PulsePilor.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        initialiseBot();
        processInput();
        terminateBot();
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
