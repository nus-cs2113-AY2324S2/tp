package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.FlowerDictionary;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import florizz.objects.Flower;

import java.util.ArrayList;
import java.util.concurrent.Flow;

public class AddFlowerCommand extends Command{
    private String flowerName;
    private Integer quantity;
    private String bouquetName;

    public AddFlowerCommand(String flowerName, int quantity, String bouquetName) {
        this.flowerName = flowerName;
        this.quantity = quantity;
        this.bouquetName = bouquetName;
    }

    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        boolean doesBouquetExist = false;
        Bouquet bouquetToAddFlower = new Bouquet();
        for (int i = 0; !doesBouquetExist && i < bouquetList.size(); i++) {
            if (bouquetList.get(i).getBouquetName().equals(this.bouquetName)) {
                bouquetToAddFlower = bouquetList.get(i);
                doesBouquetExist = true;
            }
        }

        if (!doesBouquetExist) {
            throw new FlorizzException("ERROR: No such bouquet is found." + System.lineSeparator() +
                                       "Create a bouquet by using 'new <bouquetName>`");
        }

        boolean doesFlowerExist = false;
        FlowerDictionary flowerDictionary = new FlowerDictionary();
        Flower flowerToBeAdded = new Flower();
        for (int i = 0; !doesFlowerExist && i < flowerDictionary.size(); i++) {
            if (flowerDictionary.get(i).getFlowerName().equals(flowerName)) {
                flowerToBeAdded = flowerDictionary.get(i);
                doesFlowerExist = true;
            }
        }

        if (!doesFlowerExist) {
            throw new FlorizzException("Mentioned flower is not in our database." + System.lineSeparator() +
                                       "Check available flowers: `flower` " + System.lineSeparator() +
                                       "Add custom flowers: {{TO BE DONE}}");
        }

        bouquetToAddFlower.addFlower(flowerToBeAdded, this.quantity);
        ui.printAddFlowerSuccess(bouquetList, flowerName, quantity, bouquetName);
        return true;
    }
}
