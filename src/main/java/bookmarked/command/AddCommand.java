package bookmarked.command;

import bookmarked.Book;
import bookmarked.exceptions.EmptyArgumentsException;
import bookmarked.storage.BookStorage;
import bookmarked.ui.Ui;

import java.io.File;
import java.util.ArrayList;

public class AddCommand extends Command {
    private String newItem;
    private ArrayList<Book> listOfBooks;
    private String[] splitItem;
    private File bookDataFile;
    public AddCommand(String newItem, ArrayList<Book> listOfBooks, String[] splitItem, File bookDataFile){
        this.newItem = newItem;
        this.listOfBooks = listOfBooks;
        this.splitItem = splitItem;
        this.bookDataFile = bookDataFile;
    }

    @Override
    public void handleCommand() {
        assert newItem != null : "Item should not be null";
        String[] newSplitBook = this.newItem.split("add");
        try {
            processAddCommand(newSplitBook, listOfBooks);
            assert newSplitBook.length >= 1 : "There should be an argument to the command";
            assert !this.listOfBooks.isEmpty() : "The current list of books should not be empty";
            BookStorage.writeBookToTxt(this.bookDataFile, listOfBooks);
        } catch (EmptyArgumentsException e) {
            Ui.printEmptyArgumentsMessage();
        }
    }

    public void processAddCommand(String[] newSplitBook, ArrayList<Book> listOfBooks)
            throws EmptyArgumentsException {
        // checks if newSplitBook contains only the word "add" or if there are only white spaces after it
        if (newSplitBook.length < 1 || newSplitBook[1].isBlank()) {
            throw new EmptyArgumentsException();
        }
        Book bookName = new Book(newSplitBook[1].trim());
        this.listOfBooks.add(bookName);
        System.out.println("Added " + bookName + "!");
    }
}
