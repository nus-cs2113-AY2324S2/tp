package command;

public class OrderHelpCommand implements OrderCommand {
    public static void execute() {
        System.out.println("Here are the list of available commands:");
        System.out.println("\thelp: Shows all the commands that can be used.");
        System.out.println("\tadd -item <item_id> -quantity <quantity_of_item> : Shows the menu of the restaurant.");
        System.out.println("\tdelete -item <item_id> -quantity <quantity_of_item> : Shows the menu of the restaurant.");
        System.out.println("\tview menu: Shows the menu of the current order.");
        System.out.println("\tview item: Shows the items in the current order.");
        System.out.println("\tcomplete: Completes the order and returns to the main menu.");
        System.out.println("\tbye: Aborts the current order and returns to the main menu.");
    }
}
