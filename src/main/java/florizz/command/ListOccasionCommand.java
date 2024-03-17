package florizz.command;

import florizz.core.Ui;
import florizz.objects.Bouquet;

import java.util.ArrayList;

public class ListOccasionCommand extends Command{
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) {
        ui.printAllOccasion();
        return true;
    }
}
