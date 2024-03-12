package seedu.git;

import java.util.Scanner;

import seedu.grocery.Grocery;
import seedu.grocery.GroceryList;

public class GiT {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = "   _______ ______\n"
                    + "  / ____(_)_  __/\n"
                    + " / / __/ / / /   \n"
                    + "/ /_/ / / / /    \n"
                    + "\\____/_/ /_/     \n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    

        GroceryList groceryList = new GroceryList();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Enter command:");
            String commandLine = scanner.nextLine();
            String[] commandParts = commandLine.split(" ", 2);
            String command = commandParts[0];

            switch (command.toLowerCase()) {
                case "add":
                    // Assuming the format is "add GROCERY"
                    String[] addItemParts = commandParts[1].split("g/", 2);
                    groceryList.addGrocery(new Grocery(addItemParts[1], "", ""));
                    break;
                case "exp":
                    // Assuming the format is "exp GROCERY d/EXPIRATION_DATE"
                    String[] expParts = commandParts[1].split(" d/", 2);
                    //groceryList.setExpiration(expParts[0], expParts[1]);
                    break;
                case "amt":
                    // Assuming the format is "amt GROCERY a/AMOUNT"
                    String[] amtParts = commandParts[1].split(" a/", 2);
                    //groceryList.setAmount(amtParts[0], amtParts[1]);
                    break;
                case "del":
                    //groceryList.removeGrocery(commandParts[1]);
                    break;
                case "list":
                    groceryList.listGroceries();
                    break;
                case "help":
                    displayHelp();
                    break;
                case "exit":
                    System.out.println("bye bye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for a list of commands.");
                    break;
            }
        }

        scanner.close();
    }

    private static void displayHelp() {
        System.out.println("Here are some ways you can use this app!\n" +
                "add GROCERY: adds the item GROCERY\n" +
                "exp GROCERY d/EXPIRATION_DATE: sets the expiration date for GROCERY\n" +
                "amt GROCERY a/AMOUNT: sets the amount of GROCERY\n" +
                "del GROCERY: deletes GROCERY\n" +
                "list: shows list of all groceries you have\n" +
                "exit: exits the program.");
    }
}
