package florizz.command;

import florizz.core.Ui;
import florizz.objects.Bouquet;

import java.util.ArrayList;

public class ExitCommand extends Command {

    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) {
        ui.printExitMessage();
        return true;
    }
}
