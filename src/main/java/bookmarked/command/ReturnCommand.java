package bookmarked.command;

import bookmarked.Book;
import bookmarked.exceptions.emptyListException;
import bookmarked.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReturnCommand extends Command {
    private String bookName;
    private ArrayList<Book> listOfBooks;
    public ReturnCommand(String[] commandParts, ArrayList<Book> listOfBooks) {
        assert commandParts != null : "commandParts should not be null";
        assert commandParts.length > 1 : "commandParts should contain at least the command and the book name";
        this.bookName =  String.join(" ", List.of(commandParts).subList(1, commandParts.length));
        this.listOfBooks = listOfBooks;
    }

    @Override
    public void handleCommand() {
        // Filter the list for books that match the name
        List<Book> foundBooks = listOfBooks.stream()
                .filter(book -> book.getName().equalsIgnoreCase(bookName))
                .collect(Collectors.toList());
        assert !foundBooks.isEmpty() : "Book should exist to return";

        try {
            runReturnCommand(foundBooks);
        } catch (emptyListException e) {
            Ui.printEmptyListMessage();
        }
    }

    public void runReturnCommand(List<Book> foundBooks) throws emptyListException {
        if (this.listOfBooks.isEmpty()) {
            throw new emptyListException();
        }

        if (!foundBooks.isEmpty()) {
            // It's possible there are multiple copies of the book, so mark all as returned
            foundBooks.forEach(book -> {
                //assert book.isBorrowed() : "Book should be borrowed to return";
                book.setReturned();
                System.out.println("Returned " + book.getName() + "!");
            });
        } else {
            System.out.println("Book not found: " + bookName);
        }
    }
}
