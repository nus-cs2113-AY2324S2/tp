package seedu.voyagers.commands;

import seedu.voyagers.Storage;
import seedu.voyagers.TripList;
import seedu.voyagers.Ui;


public class HelpCommand extends Command{

    @Override
    public void execute(TripList tasks, Ui ui, Storage storage) {
        ui.echo("Here are the commands you can use:", true, false);
        ui.echo("1. list - Lists all trips", false, false);
        ui.echo("2. add - Adds a trip", false, false);
        ui.echo("3. delete - Deletes a trip", false, false);
        ui.echo("4. find - Finds a trip", false, false);
        ui.echo("5. help - Shows the help message", false, false);
        ui.echo("6. exit - Exits the program",false, true);
    }
}
