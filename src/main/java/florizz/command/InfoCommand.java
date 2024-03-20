package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.Ui;
import florizz.objects.Bouquet;

import java.util.ArrayList;

public class InfoCommand extends Command{
    String flowerName;

    public InfoCommand(String flowerName) {
        this.flowerName = flowerName;
    }

    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        boolean flowerExists = ui.printFlowerInfo(flowerName);
        if (!flowerExists) {
            throw new FlorizzException("Flower does not exist type 'flower' for a list of flowers");
        }
        return true;
    }
}
