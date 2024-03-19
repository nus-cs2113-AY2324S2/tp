package seedu.bookbuddy;

public class Parser {
    public static final String ADD_COMMAND = "add";
    public static final String REMOVE_COMMAND = "remove";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String EXIT_COMMAND = "bye";

    public static void parseCommand( String input, BookList Books) {
        String[] inputArray = input.split(" ", 2);
        String command = inputArray[0].toLowerCase();
        int index;

        switch (command) {
        case ADD_COMMAND:
            Books.addBook(inputArray[1]);
            break;
        case REMOVE_COMMAND:
            index = Integer.parseInt(inputArray[1]);
            Books.deleteBook(index);
            break;
        case LIST_COMMAND:
            Books.printAllBooks();
            break;
        case MARK_COMMAND:
            index = Integer.parseInt(inputArray[1]);
            Books.markDoneByIndex(index);
            break;
        case UNMARK_COMMAND:
            index = Integer.parseInt(inputArray[1]);
            Books.markUndoneByIndex(index);
            break;
        case EXIT_COMMAND:
            BookBuddy.printExitMessage();
            System.exit(0);
            break;
        default:
            System.out.println("Sorry but that is not a valid command. Please try again");
        }
    }

}
