package florizz.command;

import florizz.core.Ui;
import florizz.objects.Bouquet;

import java.util.ArrayList;

public class AddFlowerCommand extends Command{
    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) {
        return true;
    }
}
