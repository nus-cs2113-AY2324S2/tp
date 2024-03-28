package bookmarked.command;

import bookmarked.Book;
import bookmarked.exceptions.EmptyArgumentsException;
import bookmarked.exceptions.EmptyListException;
import bookmarked.exceptions.IndexOutOfListBounds;
import bookmarked.storage.BookStorage;
import bookmarked.ui.Ui;

import java.io.File;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    private String[] bookToDelete;
    private ArrayList<Book> listOfBooks;
    private File bookDataFile;
    public DeleteCommand(String[] bookToDelete, ArrayList<Book> listOfBooks, File bookDataFile) {
        this.bookToDelete = bookToDelete;
        this.listOfBooks = listOfBooks;
        this.bookDataFile = bookDataFile;
    }

    @Override
    public void handleCommand() {
        try {
            processDeleteCommand(listOfBooks);
            assert this.bookToDelete.length >= 1 : "There should be an argument to the command";
            assert !this.listOfBooks.isEmpty() : "The current list of books should not be empty";
            BookStorage.writeBookToTxt(bookDataFile, listOfBooks);
        } catch (EmptyListException e) {
            Ui.printEmptyListMessage();
        } catch (EmptyArgumentsException e) {
            Ui.printEmptyArgumentsMessage();
        } catch (IndexOutOfListBounds e) {
            Ui.printOutOfBoundsMessage();
        } catch (NumberFormatException e) {
            Ui.printNotNumberMessage();
        }
    }

    public void processDeleteCommand(ArrayList<Book> listOfBooks)
            throws EmptyListException, EmptyArgumentsException, IndexOutOfListBounds {
        if (listOfBooks.isEmpty()) {
            throw new EmptyListException();
        }

        // checks if bookToDelete contains only the word "delete" or if there are only white spaces after it
        if (this.bookToDelete.length < 1 || this.bookToDelete[1].isBlank()) {
            throw new EmptyArgumentsException();
        }

        int inputtedIndex = Integer.parseInt(this.bookToDelete[1]);
        if (inputtedIndex <= 0 || inputtedIndex > listOfBooks.size()) {
            throw new IndexOutOfListBounds();
        }

        int listNumberIndex = (inputtedIndex - 1);
        Book bookName = listOfBooks.get(listNumberIndex);
        System.out.println("Deleted " + bookName + "!");
        this.listOfBooks.remove(listNumberIndex);
    }

}
