package bookmarked;

import bookmarked.parser.Parser;
import bookmarked.ui.Ui;
import bookmarked.storage.BookStorage;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class BookMarked {
    private static final String BOOK_FILE_PATH = "./book.txt";
    public static ArrayList<Book> listOfBooks = new ArrayList<>();
    public static int numberOfBooks = 0;

    public static void main(String[] args) {
        Ui.greetings();

        File bookDataFile = BookStorage.createFile(BOOK_FILE_PATH);
        listOfBooks = BookStorage.readFileStorage(bookDataFile);

        Scanner in = new Scanner(System.in);
        String newItem = in.nextLine();

        Parser.runCommand(newItem, in, listOfBooks, bookDataFile);
    }
}
