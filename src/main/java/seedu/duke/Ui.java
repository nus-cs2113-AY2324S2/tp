package seedu.duke;
import java.util.Random;

/**
 * The Ui class handles user interface interactions.
 */
public class Ui {
    private String[] byeSentences = {
        "AeroCade dismissed. Fly with honor and return victorious.",
        "Departure clearance granted. Execute your aero-maneuvers with expertise.",
        "Aero-Launch sequence initiated. Depart with purpose.",
        "Permission to depart granted. Execute your flight plan with discipline."
    };

    private String logo = "\n\n\n" +
            " _______  _______  ______    _______    \n" +
            "|   _   ||       ||    _ |  |       |   \n" +
            "|  |_|  ||    ___||   | ||  |   _   |   \n" +
            "|       ||   |___ |   |_||_ |  | |  |   \n" +
            "|       ||    ___||    __  ||  |_|  |   \n" +
            "|   _   ||   |___ |   |  | ||       |   \n" +
            "|__| |__||_______||___|  |_||_______|   \n" +
            " _______  _______  ______   _______     \n" +
            "|       ||   _   ||      | |       |    \n" +
            "|       ||  |_|  ||  _    ||    ___|    \n" +
            "|       ||       || | |   ||   |___     \n" +
            "|      _||       || |_|   ||    ___|    \n" +
            "|     |_ |   _   ||       ||   |___     \n" +
            "|_______||__| |__||______| |_______|    \n" ;

    public void println(String s) {
        System.out.println(s);
    }

    
    public void greetUser() {
        println(logo + "\n[Welcome to the AeroCade]");
        println("What can I do for you?\nType 'help' for a list of available commands!\n");
    }

    public void byeUser() {
        int randomIndex = new Random().nextInt(byeSentences.length);
        println("\n" + byeSentences[randomIndex] + "\n");
    }

    public void printHelp() {
        println("help menu to be added");
    }
}


