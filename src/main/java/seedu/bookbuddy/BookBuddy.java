package seedu.bookbuddy;

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
            String userInput = input.nextLine();
            Parser.parseCommand(userInput, books);
        }
    }

    public static void printExitMessage() {
        System.out.println("Thank you for using BookBuddy! Hope to see you again!");
    }

}
