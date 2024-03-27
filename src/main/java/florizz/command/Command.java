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
     * Executes the command
     * @param bouquetList list of bouquets to be manipulated by the command
     * @param ui ui class for printing
     * @return isRunning, if false program will terminate
     * @throws FlorizzException catch all exception class
     */
    public abstract boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException;
}
