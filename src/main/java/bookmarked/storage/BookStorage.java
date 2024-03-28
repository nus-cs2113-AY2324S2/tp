package bookmarked.storage;

import bookmarked.Book;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

public class BookStorage {
    public static ArrayList<Book> listOfBooks = new ArrayList<>();
    public static File createFile(String bookDataPath) {
        File bookDataFile = new File(bookDataPath);
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

    public static ArrayList<Book> readFileStorage (File bookDataFile) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(bookDataFile));
            String currentTextLine = fileReader.readLine();
            int bookCount = 0;

            while (currentTextLine != null) {
                addToArrayList(currentTextLine, bookCount, listOfBooks);
                bookCount += 1;
                currentTextLine = fileReader.readLine();
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Access to file is interrupted");
        }

        return listOfBooks;
    }

    public static void writeBookToTxt(File bookDataFile, ArrayList<Book> listOfBooks) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(bookDataFile, false));
            int bookCount = listOfBooks.size();
            for (int i = 0; i < bookCount; i += 1) {
                writeFormattedString(i, fileWriter, listOfBooks);
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!!!");
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }

    private static void writeFormattedString(int bookIndex, BufferedWriter fileWriter, ArrayList<Book> listOfBooks)
            throws IOException {
        Book currentBook = listOfBooks.get(bookIndex);
        String bookTitle = currentBook.getName();
        String bookBorrowStatus = currentBook.getBorrowedStatus().equals(", borrowed") ? "True" : "False";
        fileWriter.write(bookTitle + " | " + bookBorrowStatus + "\n");
    }

    private static void addToArrayList(String currentTextLine, int bookCount, ArrayList<Book> listOfBooks) {
        String[] splitTextLine;
        splitTextLine = currentTextLine.split(" \\| ");
        listOfBooks.add(new Book(splitTextLine[0]));

        // Update borrow status in array list
        if (splitTextLine[1].equalsIgnoreCase("True")) {
            Book currentBook = listOfBooks.get(bookCount);
            currentBook.setBorrowed();
        }
    }
}
