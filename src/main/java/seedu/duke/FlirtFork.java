package seedu.duke;

import seedu.duke.exceptions.FlirtForkException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FlirtFork {
    private static final String FILE_PATH = "./data/FlirtFork.txt";
    private static final String HORIZONTAL = "____________________________________________________________";
    private FavouritesList favourites;
    private Ui ui;
    private Storage storage;

    public FlirtFork(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            favourites = new FavouritesList(storage.loadFavourites());
        } catch (FileNotFoundException e) {
            ui.errorMessage("File not found. Starting with an empty task list :)");
            favourites = new FavouritesList(new ArrayList<>());
        }
    }

    public void run() {
        ui.greetingMessage();

        boolean isExit = false;
        while(!isExit) {
            String userInput = ui.readCommand();
            try {
                Command command = Parser.parseCommand(userInput);
                command.execute(favourites, ui, storage);
                if(command instanceof ExitCommand) {
                    isExit = true;
                }
                System.out.println(HORIZONTAL);
            } catch (FlirtForkException e) {
                ui.errorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        FlirtFork FlirtFork = new FlirtFork(FILE_PATH);
        FlirtFork.run();
    }
}
