package seedu.bookbuddy;

import java.util.Objects;
import java.util.Scanner;

public class BookBuddy {
    private static BookList bookList = new BookList();
    public static void main(String[] args) {

        System.out.println("Hello! We are bookbuddy!");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            String command = input.split(" ", 2)[0];
            if (Objects.equals(command, "addBook")) {
                String actualDescription = input.split(" ", 2)[1];
                bookList.addBook(actualDescription);
            } else if (Objects.equals(command, "list")) {
                bookList.printAllBooks();
            }
        }
    }

}
