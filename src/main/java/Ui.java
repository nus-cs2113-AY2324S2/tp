import java.util.Random;

/**
 * The Ui class handles user interface interactions.
 */
public class Ui {

    public void println(String s) {
        System.out.println(s);
    }

    private static final String[] byeSentences = {
            "AeroCade dismissed. Fly with honor and return victorious.",
            "Departure clearance granted. Execute your aero-maneuvers with expertise.",
            "Aero-Launch sequence initiated. Depart with purpose.",
            "Permission to depart granted. Execute your flight plan with discipline."
    };
    
    private final static String logo = "\n\n\n" +
            " ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄    \n" +
            "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌   \n" +
            "▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌   \n" +
            "▐░▌       ▐░▌▐░▌          ▐░▌       ▐░▌▐░▌       ▐░▌   \n" +
            "▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌   \n" +
            "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌   \n" +
            "▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀█░█▀▀ ▐░▌       ▐░▌   \n" +
            "▐░▌       ▐░▌▐░▌          ▐░▌     ▐░▌  ▐░▌       ▐░▌   \n" +
            "▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░▌      ▐░▌ ▐░█▄▄▄▄▄▄▄█░▌   \n" +
            "▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌   \n" +
            " ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀    \n" +
                                                                   "\n" +
            "▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄     \n" +
            "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌   \n" +
            "▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀    \n" +
            "▐░▌          ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌             \n" +
            "▐░▌          ▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄▄▄    \n" +
            "▐░▌          ▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌   \n" +
            "▐░▌          ▐░█▀▀▀▀▀▀▀█░▌▐░▌       ▐░▌▐░█▀▀▀▀▀▀▀▀▀    \n" +
            "▐░▌          ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌             \n" +
            "▐░█▄▄▄▄▄▄▄▄▄ ▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄    \n" +
            "▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌   \n" +
            " ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀   ▀▀▀▀▀▀▀▀▀▀▀    \n";

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


