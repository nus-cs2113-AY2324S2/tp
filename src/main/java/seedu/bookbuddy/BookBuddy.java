package seedu.bookbuddy;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BookBuddy {
    public static ArrayList<BookDetails> bookDetailsList = new ArrayList<>();
    public static void main(String[] args) {

        System.out.println("Hello! We are bookbuddy!");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            String command = input.split(" ", 2)[0];
            if (Objects.equals(command, "addBook")) {
                addBook(input);
            } else if (Objects.equals(command, "list")) {
                printList();
            }
        }
    }

    public static void addBook(String input) {
        String actualDescription = input.split(" ", 2)[1];
        BookDetails newTodo = new BookDetails(actualDescription);
        bookDetailsList.add(newTodo);
    }

    public static void printList() {
        System.out.println("Here are the current books in your list:");
        if (bookDetailsList.isEmpty()) {
            System.out.println("Great job! You have no tasks!");
        } else {
            for (int i = 0; i < bookDetailsList.size(); i++) {
                System.out.println((i + 1) + "." + bookDetailsList.get(i));
            }
        }
    }


}
