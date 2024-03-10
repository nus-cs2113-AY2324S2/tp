package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.Ui;
import florizz.objects.Bouquet;

import java.util.ArrayList;

public class DeleteBouquetCommand extends Command{
    private final Bouquet bouquetToDelete;
    public DeleteBouquetCommand(Bouquet bouquetToDelete){
        this.bouquetToDelete = bouquetToDelete;
    }
    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        if (!bouquetList.contains(bouquetToDelete)){
            throw new FlorizzException("Could not find bouquet to delete");
        }
        bouquetList.remove(bouquetToDelete);
        ui.printBouquetDeleted(bouquetToDelete);
        return true;
    }
}
