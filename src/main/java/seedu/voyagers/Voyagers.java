package seedu.voyagers;

import seedu.voyagers.classes.TripList;
import seedu.voyagers.utils.Ui;
import seedu.voyagers.commands.Command;
import seedu.voyagers.commands.ListCommand;

import java.util.ArrayList;

import static seedu.voyagers.utils.Storage.readTripFile;
import static seedu.voyagers.utils.Storage.writeTripFile;
import java.util.logging.*;

public class Voyagers {

    private static final String FILE_NAME = "local-voyagers.txt";

    //TODO: change to private and add to the command.execute(Ui, tripList, storage)
    public TripList tripList;
    public Ui ui;

    public Voyagers() {
        this.tripList = new TripList(new ArrayList<>());
        this.ui = new Ui();
    }
    public static void main(String[] args) {
        new Voyagers().run();
    }

    void run() {

        Logger logger = Logger.getLogger("Voyagers");
        logger.setLevel(Level.INFO);

        ui.showWelcome();
        assert false : "This is a debug assertion set to fail.";
        ui.echo("Here are the trips in your list from the previous time:", false, false);

        //TODO: make Storage a singleton
        String currentDir = System.getProperty("user.dir");
        //TODO: check
        readTripFile(tripList.getTrips(), currentDir, FILE_NAME);


        new ListCommand().execute(tripList, ui, null);
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseInput(fullCommand);
                c.execute(tripList, ui, null);
                isExit = c.isExit();

            } catch (Exception e) { //TODO: change to specific exceptions
                ui.echo(e.getMessage());
            }
        }

        writeTripFile(tripList.getTrips(), tripList.size(), currentDir);
    }

}
