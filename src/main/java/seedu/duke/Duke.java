package seedu.duke;

import parser.Parser;
import ui.TextUi;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final TextUi ui = new TextUi();
    private final Parser parser = new Parser();
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }

    public void run() throws IOException {
        ui.showWelcomeMessage("Version 1.0", "Storage File Path"); //Replace with variable
        this.normalOperation();
        ui.showGoodByeMessage("Storage File Path"); //Replace with variable
    }

    private void normalOperation() throws IOException {
        String userInput;
        do {
            userInput = ui.getUserInput();
            parser.parseInput(userInput);
        } while (!userInput.equals("done"));
    }
}
