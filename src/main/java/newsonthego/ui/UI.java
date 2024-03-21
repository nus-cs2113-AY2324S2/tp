package newsonthego.ui;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UI {
    private static final Logger logger = Logger.getLogger("NewsOnTheGo");

    public static void initializeUI(Scanner in) {
        logger.log(Level.INFO, "Starting NewsOnTheGo");
        String logo = "\n" +
                ",-,-.                 ,---.     ,--,--'.       ,---.      \n" +
                "` | |   ,-. . , , ,-. |   | ,-. `- |   |-. ,-. |  -'  ,-. \n" +
                "  | |-. |-' |/|/  `-. |   | | |  , |   | | |-' |  ,-' | | \n" +
                " ,' `-' `-' ' '   `-' `---' ' '  `-'   ' ' `-' `---|  `-' \n" +
                "                                                ,-.|      \n" +
                "                                                `-+'      \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        System.out.println("Hello " + in.nextLine());

    }

    public static void printError(String message) {
        System.err.println(message);
    }
}

