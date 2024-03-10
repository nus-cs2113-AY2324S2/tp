package florizz.command;
import florizz.core.FlorizzException;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import java.util.ArrayList;

/**
 * Abstract class for all commands to inherit
 */
public abstract class Command {
    /**
     *
     * @param ui ui class for printing

     */
    public abstract boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException;
}