package bookmarked;

import bookmarked.Book;
import bookmarked.parser.Parser;
import bookmarked.ui.Ui;

import java.util.Scanner;
import java.util.ArrayList;

public class BookMarked {

    public static ArrayList<Book> listOfBooks = new ArrayList<>();
    public static int numberOfBooks = 0;

    public static void main(String[] args) {
        Ui.greetings();

        Scanner in = new Scanner(System.in);
        String newItem = in.nextLine();

        Parser.runCommand(newItem, in, listOfBooks);
    }
}
