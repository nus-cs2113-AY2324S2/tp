package florizz.command;

import florizz.core.Ui;
import florizz.objects.Bouquet;

import java.util.ArrayList;

public class HelpCommand extends Command{
    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) {
        ui.printHelpMessage();
        return true;
    }
}