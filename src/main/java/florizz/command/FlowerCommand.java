package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.OccasionDictionary;
import florizz.core.Ui;
import florizz.objects.Bouquet;

import java.util.ArrayList;

public class FlowerCommand extends Command{
    private String occasion;

    public FlowerCommand(String occasion) {
        this.occasion = occasion;
    }

    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        if (occasion.isBlank()) {
            ui.printAllDictFlowerName();
        } else if (!OccasionDictionary.contains(occasion)){
            throw new FlorizzException("This occasion does not exist type 'occasion' to get a list of occasions");
        } else {
            ui.printOccasionFlower(occasion);
        }
        return true;
    }
}
