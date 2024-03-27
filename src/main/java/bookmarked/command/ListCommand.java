package bookmarked.command;

import bookmarked.Book;
import bookmarked.exceptions.EmptyListException;
import bookmarked.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    ArrayList<Book> listOfBooks;
    public ListCommand(ArrayList<Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    @Override
    public void handleCommand() {
        try {
            runListCommand();
        } catch (EmptyListException e) {
            Ui.printEmptyListMessage();
        }
    }

    public void runListCommand() throws EmptyListException {
        if (this.listOfBooks.isEmpty()) {
            throw new EmptyListException();
        }

        int numberOfBooks = this.listOfBooks.size();
        System.out.println("Here are your list items!");
        for (int i = 0; i < numberOfBooks; i++) {
            System.out.println((i + 1) + ". " + this.listOfBooks.get(i).toString());
        }
    }
}
