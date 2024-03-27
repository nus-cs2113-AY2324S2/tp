package seedu.duke;

import command.Command;
import command.ExitCommand;
import exceptions.CommandFormatException;
import parser.Parser;
import storage.Storage;
import ui.TextUi;

import itemlist.Itemlist;

import java.io.IOException;

public class Duke {
    private final TextUi ui = new TextUi();
    private final Parser parser = new Parser();
    private Itemlist itemlist = new Itemlist();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws IOException, CommandFormatException {
        new Duke().run();
    }

    public void run() throws IOException, CommandFormatException {
        Storage.readFromFile("./StockMasterData.txt");
        ui.showWelcomeMessage("1.0", "./StockMasterData.txt");
        this.normalOperation();
        ui.showGoodByeMessage("./StockMasterData.txt");
    }

    private void normalOperation() throws IOException, CommandFormatException {
        String userInput;
        do {
            userInput = ui.getUserInput();
            Command command = parser.parseInput(userInput);
            command.execute();
        } while (!ExitCommand.getIsExit());
    }

}
