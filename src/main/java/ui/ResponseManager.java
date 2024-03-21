package ui;

import player.PlayerProfile;

public class ResponseManager {
    private static final String INITIALIZATION_MESSAGE = "Initializing...\n"
        + "Enter your name: \n";
    private static final String INDENTATION =
            "===".repeat(10) + "\n";
    private static final String JOB_SELECT_MSG = "Choose your job type: \n" +
            "for Robotics, enter '/r'\n" +
            "for Semiconductor industry, enter '/s'\n" +
            "for Artificial intelligence, enter '/a'\n";
    private static final String BYE_MSG = "Bye bye adventurer!";
    private static final String HELP_MSG =
            "Enter ur action!\n" +
            "work - to work\n" +
            "rest - to rest\n" +
            "exercise - to exercise\n" +
            "status - to check status\n" +
            "bye - to exit\n";

    public static void printBoard(String boardInfo) {
        indentPrint(boardInfo + "\n");
    }

    public static void indentPrint(String message) {
        System.out.println(INDENTATION + message + INDENTATION);
    }

    public static void printGameInit() {
        indentPrint(INITIALIZATION_MESSAGE);
    }

    public static void printJobSelect() {
        indentPrint(JOB_SELECT_MSG);
    }

    public static void printWelcome(PlayerProfile playerProfile) {
        System.out.println("Welcome, " + playerProfile.toString());
    }

    public static void echoChosenIndustry(String input) {
        System.out.println("You have chosen " + input);
    }

    public static void printGoodbye() {
        System.out.println(BYE_MSG);
    }

    public static void printHelp() {
        indentPrint(HELP_MSG);
    }
}
