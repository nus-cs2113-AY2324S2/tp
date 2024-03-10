package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.Ui;
import florizz.objects.Bouquet;

import java.util.ArrayList;

public class AddBouquetCommand extends Command{
    private final Bouquet bouquetToAdd;
    public AddBouquetCommand(Bouquet bouquetToAdd){
        this.bouquetToAdd = bouquetToAdd;
    }
    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        if (bouquetList.contains(bouquetToAdd)){
            throw new FlorizzException("Tried to add bouquet already in list");
        }
        bouquetList.add(bouquetToAdd);
        ui.printBouquetAdded(bouquetToAdd);
        return true;
    }
}
