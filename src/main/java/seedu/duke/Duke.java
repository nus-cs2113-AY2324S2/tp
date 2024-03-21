package seedu.duke;

import command.Command;
import command.ExitCommand;
import exceptions.CommandFormatException;
import parser.Parser;
import ui.TextUi;

import itemlist.Itemlist;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final TextUi ui = new TextUi();
    private final Parser parser = new Parser();
    private Itemlist itemlist = new Itemlist();
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        new Duke().run();
    }

    public void run() throws IOException {
        ui.showWelcomeMessage("1.0", "./StockMasterData.txt");
        this.normalOperation();
        ui.showGoodByeMessage("./StockMasterData.txt");
    }

    private void normalOperation() throws IOException {
        String userInput;
        do {
            userInput = ui.getUserInput();
            Command command = parser.parseInput(userInput);
            command.execute();
        } while (!ExitCommand.getIsExit());
    }
}
