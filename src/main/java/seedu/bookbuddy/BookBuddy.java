package seedu.bookbuddy;

import exceptions.UnsupportedCommandException;

import java.util.Scanner;


public class BookBuddy {
    private static BookList books = new BookList();
    public static void main(String[] args) {
        Ui.printWelcome();
        //printWelcomeMessage();
        getUserInput(books);
    }


    public static void getUserInput(BookList books) {
        Scanner input = new Scanner(System.in);

        //noinspection InfiniteLoopStatement
        while (true) {
            if (!input.hasNextLine()) { // Check if there is another line of input
                break; // Exit the loop if there is no input
            }
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                // If the input is empty, do not call parseCommand and just prompt for input again.
                continue;
            }

            try {
                Parser.parseCommand(userInput, books);
            } catch (UnsupportedCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
