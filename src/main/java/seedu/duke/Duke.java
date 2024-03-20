package seedu.duke;

import parser.Parser;
import Itemlist.Itemlist;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final TextUi ui = new TextUi();
    private final Parser parser = new Parser();
    private Itemlist itemlist = new Itemlist();
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
        ui.showWelcomeMessage();
        this.normalOperation();
        ui.showGoodByeMessage();
    }

    private void normalOperation() throws IOException {
        String userInput;
        do {
            userInput = ui.getUserCommand();
            parser.parseInput(userInput);
        } while (!userInput.equals("done"));
    }
}
