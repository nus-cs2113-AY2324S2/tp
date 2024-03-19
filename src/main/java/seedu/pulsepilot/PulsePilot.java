package seedu.pulsepilot;

import utility.CustomExceptions;

import static ui.Handler.initialiseBot;
import static ui.Handler.processInput;
import static ui.Handler.terminateBot;

public class PulsePilot {
    /**
     * Main entry-point for PulsePilot.
     */
    public static void main(String[] args) throws CustomExceptions.InvalidInput {
        initialiseBot();
        processInput();
        terminateBot();


    }
}
