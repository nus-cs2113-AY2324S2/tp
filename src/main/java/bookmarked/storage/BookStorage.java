package bookmarked.storage;

import bookmarked.Book;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class BookStorage {
    private String bookDataPath;
    private String dataDirectory;

    private ArrayList<Book> listOfBooks;

    public BookStorage(String bookDataPath, String dataDirectory) {
        this.bookDataPath = bookDataPath;
        this.dataDirectory = dataDirectory;
    }

    public File createFile() {
        File bookDataFile = new File(this.bookDataPath);
        try {
            boolean isFileCreated = bookDataFile.createNewFile();

            if (isFileCreated) {
               System.out.println("Successfully create a storage file for books");
            } else {
                System.out.println("Sorry, something's wrong, file is not created");
            }
        } catch (IOException e) {
            System.out.println("Sorry, something's wrong, file is not created");
        }
        return bookDataFile;
    }
}
