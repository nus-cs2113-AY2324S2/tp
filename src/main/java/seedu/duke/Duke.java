package seedu.duke;
import java.util.Scanner;

import static ui.Handler.initialiseBot;
import static ui.Handler.processInput;
import static ui.Handler.terminateBot;

public class Duke {
    /**
     * Main entry-point for PulsePilot.
     */
    public static void main(String[] args) {
        initialiseBot();
        processInput();
        terminateBot();
    }
}
