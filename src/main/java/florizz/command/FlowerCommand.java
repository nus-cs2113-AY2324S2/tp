package florizz.command;

import florizz.core.FlorizzException;
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
        } else {
            ui.printOccasionFlower(occasion);
        }
        return true;
    }
}
