package seedu.bookbuddy;

import exceptions.UnsupportedCommandException;

import java.util.Scanner;

public class BookBuddy {
    private static BookList books = new BookList();
    public static void main(String[] args) {
        printWelcomeMessage();
        getUserInput(books);
    }

    public static void printWelcomeMessage() {
        System.out.println("Hello! We are BookBuddy!");
        System.out.println("How can I help you today?");
    }

    public static void getUserInput(BookList books) {
        Scanner input = new Scanner(System.in);

        //noinspection InfiniteLoopStatement
        while (true) {
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

    public static void printExitMessage() {
        System.out.println("Thank you for using BookBuddy! Hope to see you again!");
    }

}
