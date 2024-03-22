package florizz.core;

import florizz.command.Command;
import florizz.objects.Bouquet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Florizz {
    private static Logger logger = Logger.getLogger(Florizz.class.getName());

    /**
     * Main entry-point for the java.florizz.core.Florizz application.
     */
    public static void main(String[] args) {
        // Adds flowers to the dict (temporary use)
        FlowerDictionary.startup();

        // Adds occasions to the dict (temporary use)
        OccasionDictionary.startup();
        ArrayList<Bouquet>  tempBouquetList = new ArrayList<>();
        boolean isRunning = true;
        Ui ui = new Ui();
        ui.printIntroMessage();
        assert !isRunning : "Programme is running";
        assert tempBouquetList !=null : "tempBouquetList doesn't exist"

        // Set up logger
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        // Set up console handler
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logger.addHandler(ch);

        // Set up file handler
        try {
            FileHandler fh = new FileHandler();
            fh.setLevel(Level.ALL);
            logger.addHandler(fh);
            System.out.println("FileHandler opened");
            System.out.println(Florizz.class.getClassLoader().getResource("logging.properties"));
        } catch (IOException e) {
            ui.printIOError();
        }

        while (isRunning) {
            logger.log(Level.INFO, "Entered isRunning while loop in Florizz.java");
            try {
                String input = ui.getInput();
                Command command = Parser.parse(input);
                isRunning = command.execute(tempBouquetList, ui);
            } catch(FlorizzException error){
                ui.printError(error);
            }
        }
    }
}
