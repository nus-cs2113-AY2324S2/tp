package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.FlowerDictionary;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import florizz.objects.Flower;

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
            return true;
        }
        try {
            Flower.Occasion occasionEnum = Flower.stringToOccasion(occasion);
            ui.printFilteredFlowers(FlowerDictionary.filterByOccasion(occasionEnum),occasion);
            return true;

        }catch(IllegalArgumentException error){
            throw new FlorizzException("This occasion does not exist. Type 'occasion' to get a list of occasions");
        }
    }
}
