package seedu.duke;

import java.util.Scanner;
import java.util.ArrayList;

public class BookMarked {

    public static ArrayList<Book> listOfBooks = new ArrayList<>();
    public static int numberOfBooks = 0;


    public static void main(String[] args) {
        System.out.println("Welcome to BookMarked, a one-stop app for all your librarian needs!");
        System.out.println("Type /help to see a list of commands!");

        Scanner in = new Scanner(System.in);
        String newItem = in.nextLine();

        while (!newItem.contains("bye")) {
            String[] splitItem = newItem.split(" ");
            if (splitItem[0].matches("/help")) {
                System.out.println("Type list to see your books.");
                System.out.println("Type add to add a book.");
                System.out.println("Type delete to delete a book.");
                System.out.println("Type borrow to mark a book as borrowed.");
                System.out.println("Type return to mark a book as returned.");
                System.out.println("Type bye to leave the app.");
            } else if (splitItem[0].matches("list")) {
                printList(numberOfBooks);
            } else if (splitItem[0].matches("add")) {
                String[] newSplitBook = newItem.split("add");
                listOfBooks.add(new Book(splitItem[1]));
                System.out.println("Added!");
                numberOfBooks++;
            } else if (splitItem[0].matches("delete")) {
                deleteBook(splitItem);
            } else if (splitItem[0].matches("borrow")) {
                borrowBook(splitItem);
            } else if (splitItem[0].matches("return")) {
                returnBook(splitItem);
            }

            System.out.println("__________");
            newItem = in.nextLine();

        }
        System.out.println("Bye!");

    }

    public static void printList(int numberOfBooks) {
        System.out.println("Here are your list items!");
        for (int i = 0; i < numberOfBooks; i++) {
            System.out.println((i + 1) + ". " + listOfBooks.get(i).toString());
        }
    }

    public static void deleteBook(String[] bookToDelete) {
        int inputtedIndex = Integer.parseInt(bookToDelete[1]);
        int listNumberIndex = (inputtedIndex - 1);
        System.out.println("Deleted!");
        listOfBooks.remove(listNumberIndex);
        numberOfBooks--;
    }

    public static void borrowBook(String[] bookToBorrow) {
        int inputtedIndex = Integer.parseInt(bookToBorrow[1]);
        int listNumberIndex = (inputtedIndex - 1);
        System.out.println("Borrowed!");
        listOfBooks.get(listNumberIndex).isBorrowed = true;
    }

    public static void returnBook(String[] bookToReturn) {
        int inputtedIndex = Integer.parseInt(bookToReturn[1]);
        int listNumberIndex = (inputtedIndex - 1);
        System.out.println("Returned!");
        listOfBooks.get(listNumberIndex).isBorrowed = false;
    }

}
