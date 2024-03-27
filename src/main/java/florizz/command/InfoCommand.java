package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.Ui;
import florizz.objects.Bouquet;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InfoCommand extends Command{
    private static final Logger logger = Logger.getLogger(InfoCommand.class.getName());
    private ConsoleHandler ch = new ConsoleHandler();
    private String flowerName;

    public InfoCommand(String flowerName) {
        this.flowerName = flowerName;
        logger.addHandler(ch);
    }

    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        boolean flowerExists = ui.printFlowerInfo(flowerName);
        if (!flowerExists) {
            logger.log(Level.WARNING, "FLOWER DOES NOT EXIST");
            throw new FlorizzException("Flower does not exist type 'flower' for a list of flowers");
        }
        return true;
    }
}
