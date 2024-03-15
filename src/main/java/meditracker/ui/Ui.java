package meditracker.ui;

import java.util.List;
import java.util.Scanner;

/**
 * The Ui class handles user interface-related operations.
 * It includes methods to display welcome messages, exit messages, and read user commands.
 */
public class Ui {
    Scanner input = new Scanner(System.in);

    /**
     * Displays the welcome message and introduction name.
     */
    public void showWelcomeMessage() {
        printIntroName();
        showWelcome();
    }

    /**
     * Prints the introduction name banner.
     */
    public void printIntroName() {

        // Solution below adapted by http://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20
        System.out.println("                    __      ______                      __                      \n" +
                " /'\\_/`\\           /\\ \\  __/\\__  _\\                    /\\ \\                     \n" +
                "/\\      \\     __   \\_\\ \\/\\_\\/_/\\ \\/ _ __    __      ___\\ \\ \\/'\\      __   _ __  \n" +
                "\\ \\ \\__\\ \\  /'__`\\ /'_` \\/\\ \\ \\ \\ \\/\\`'__\\/'__`\\   /'___\\ \\ , <    /'__`\\/\\`'__\\\n"
                +
                " \\ \\ \\_/\\ \\/\\  __//\\ \\L\\ \\ \\ \\ \\ \\ \\ \\ \\//\\ \\L\\.\\_/\\ \\__/\\ \\ \\\\`\\ /\\" +
                "  __/\\" +
                " \\ \\/ \n" +
                "  \\ \\_\\\\ \\_\\ \\____\\ \\___,_\\ \\_\\ \\ \\_\\ \\_\\\\ \\__/.\\_\\ \\____\\\\ \\_\\ \\_\\ " +
                "\\____\\" +
                "\\ \\_\\ \n" +
                "   \\/_/ \\/_/\\/____/\\/__,_ /\\/_/  \\/_/\\/_/ \\/__/\\/_/\\/____/ \\/_/\\/_/\\/____/ \\/_/ \n" +
                "                                                                                \n" +
                "                                                                                ");
    }

    /**
     * Displays a line divider.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println("Welcome to MediTracker, your best companion to track your medicine intake.");
        System.out.println("Let's begin tracking!\n");
    }

    /**
     * Displays the exit message.
     */
    public void showExitMessage() {
        System.out.println("Thank you for using MediTracker. Hope to see you again!");
    }

    public void showAddCommandMessage() {
        System.out.println("Medicine has been successfully added!");
    }

    /**
     * Reads user input command.
     * @return The user input command as a String.
     */
    public String readCommand() {
        return input.nextLine();
    }

    public <T> void printMedsList(List<T> medications, String listName) {
        System.out.println("Here are the " + listName + " you have to take today: ");
        for (T medication : medications) {
            int numbering = medications.indexOf(medication) + 1;
            System.out.println("\t" + numbering + ". " + medication);
        }
    }
}
