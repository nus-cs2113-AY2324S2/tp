package bookmarked.command;

import bookmarked.Book;
import bookmarked.exceptions.EmptyListException;
import bookmarked.storage.BookStorage;
import bookmarked.ui.Ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowCommand extends Command {
    private String bookName;
    private ArrayList<Book> listOfBooks;
    private File bookDataFile;

    public BorrowCommand(String[] commandParts, ArrayList<Book> listOfBooks, File bookDataFile) {
        assert commandParts != null : "commandParts should not be null";
        assert commandParts.length > 1 : "commandParts should contain at least two elements";
        this.bookName = String.join(" ", List.of(commandParts).subList(1, commandParts.length));
        assert listOfBooks != null : "listOfBooks should not be null";
        this.listOfBooks = listOfBooks;
        this.bookDataFile = bookDataFile;
    }

    @Override
    public void handleCommand() {
        // Find the book with the matching name
        List<Book> foundBooks = listOfBooks.stream()
                .filter(book -> book.getName().equalsIgnoreCase(bookName))
                .collect(Collectors.toList());
        try {
            runBorrowCommand(foundBooks);
            BookStorage.writeBookToTxt(bookDataFile, listOfBooks);
        } catch (EmptyListException e) {
            Ui.printEmptyListMessage();
        }
    }

    public void runBorrowCommand(List<Book> foundBooks) throws EmptyListException {
        if (this.listOfBooks.isEmpty()) {
            throw new EmptyListException();
        }

        if (!foundBooks.isEmpty()) {
            // Assuming that there can be multiple books with the same name,
            // and we mark all copies as borrowed
            foundBooks.forEach(book -> {
                //assert !book.isBorrowed() : "Book is already borrowed: " + book.getName();
                book.setBorrowed();
                System.out.println("Borrowed " + book.getName() + "!");
            });
        } else {
            System.out.println("Book not found: " + bookName);
        }
    }
}
