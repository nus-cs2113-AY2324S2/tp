package bookmarked.storage;

import bookmarked.Book;

import java.nio.Buffer;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

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

    public ArrayList<Book> readFileStorage (File bookDataFile) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(bookDataFile));
            String currentTextLine = fileReader.readLine();
            int bookCount = 0;

            while (currentTextLine != null) {
                addToArrayList(currentTextLine, bookCount);
                bookCount += 1;
                currentTextLine = fileReader.readLine();
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Access to file is interrupted");
        }

        return this.listOfBooks;
    }

    private void addToArrayList(String currentTextLine, int bookCount) {
        String[] splitTextLine;
        splitTextLine = currentTextLine.split("//|");
        this.listOfBooks.add(new Book(splitTextLine[0]));

        // Update borrow status in array list
        if (splitTextLine[1].equalsIgnoreCase("Yes")) {
            Book currentBook = this.listOfBooks.get(bookCount);
            currentBook.setBorrowed();
        }
    }
}
