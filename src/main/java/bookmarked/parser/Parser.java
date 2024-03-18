package bookmarked.parser;

import bookmarked.Book;
import bookmarked.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static void runCommand(String newItem, Scanner in, ArrayList<Book> listOfBooks) {
        int numberOfBooks = listOfBooks.size();
        while (!newItem.equalsIgnoreCase("bye")) {
            String[] splitItem = newItem.split(" ");
            if (splitItem[0].matches("/help")) {
                Ui.printHelpMessage();
            } else if (splitItem[0].matches("list")) {
                printList(numberOfBooks, listOfBooks);
            } else if (splitItem[0].matches("add")) {
                addBook(newItem, listOfBooks, splitItem);
                numberOfBooks++;
            } else if (splitItem[0].matches("delete")) {
                deleteBook(splitItem, listOfBooks);
                numberOfBooks--;
            } else if (splitItem[0].matches("borrow")) {
                borrowBook(splitItem, listOfBooks);
            } else if (splitItem[0].matches("return")) {
                returnBook(splitItem, listOfBooks);
            }

            Ui.separateNextInput();
            newItem = in.nextLine();

        }
    }

//    private static void addBook(String newItem, ArrayList<Book> listOfBooks, String[] splitItem) {
//        String[] newSplitBook = newItem.split("add");
//        listOfBooks.add(new Book(splitItem[1]));
//        System.out.println("Added!");
//    }

    public static void printList(int numberOfBooks, ArrayList<Book> listOfBooks) {
        System.out.println("Here are your list items!");
        for (int i = 0; i < numberOfBooks; i++) {
            System.out.println((i + 1) + ". " + listOfBooks.get(i).toString());
        }
    }

    public static void deleteBook(String[] bookToDelete, ArrayList<Book> listOfBooks) {
        int inputtedIndex = Integer.parseInt(bookToDelete[1]);
        int listNumberIndex = (inputtedIndex - 1);
        System.out.println("Deleted!");
        listOfBooks.remove(listNumberIndex);
//        numberOfBooks--;
    }

    public static void borrowBook(String[] bookToBorrow, ArrayList<Book> listOfBooks) {
        int inputtedIndex = Integer.parseInt(bookToBorrow[1]);
        int listNumberIndex = (inputtedIndex - 1);
        System.out.println("Borrowed!");
//        listOfBooks.get(listNumberIndex).isBorrowed = true;
        listOfBooks.get(listNumberIndex).setBorrowed();
    }

    public static void returnBook(String[] bookToReturn, ArrayList<Book> listOfBooks) {
        int inputtedIndex = Integer.parseInt(bookToReturn[1]);
        int listNumberIndex = (inputtedIndex - 1);
        System.out.println("Returned!");
//        listOfBooks.get(listNumberIndex).isBorrowed = false;
        listOfBooks.get(listNumberIndex).setReturned();
    }
}
