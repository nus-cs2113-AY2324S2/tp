package bookmarked.ui;

public class Ui {

    public static void greetings() {
        System.out.println("Welcome to BookMarked, a one-stop app for all your librarian needs!");
        System.out.println("Type /help to see a list of commands!");
    }

    public static void printHelpMessage() {
        System.out.println("These are the current available features and the format that you need");
        System.out.println("to follow to use it in using this software:");
        System.out.println("1. To add books");
        System.out.println("   add NAME_OF_BOOK");
        System.out.println("2. To delete current existing book");
        System.out.println("   delete NUMBER_ACCORDING_TO_LIST");
        System.out.println("3. To mark book as borrowed");
        System.out.println("   borrow NAME_OF_BOOK");
        System.out.println("4. To unmark book as returned");
        System.out.println("   return NAME_OF_BOOK");
        System.out.println("5. To list all the books added");
        System.out.println("   list");
    }

    public static void separateNextInput() {
        System.out.println("__________");
    }

    public static void printUnknownCommand() {
        System.out.println("Unknown command; please type /help to see what commands you can use.");
    }

    public static void printEmptyListMessage() {
        System.out.println("The list is empty, try adding a book first.");
    }

    public static void printEmptyArgumentsMessage() {
        System.out.println("Please type in the correct arguments.");
    }

    public static void printOutOfBoundsMessage() {
        System.out.println("Please enter a book index that exists on the current list.");
    }

    public static void printNotNumberMessage() {
        System.out.println("Please enter the number index of the book.");
    }

    public static void exitProgramme() {
        System.out.println("Bye!");
    }
}
