package bookmarked.command;

import bookmarked.Book;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    String[] bookToDelete;
    ArrayList<Book> listOfBooks;
    public DeleteCommand(String[] bookToDelete, ArrayList<Book> listOfBooks) {
        this.bookToDelete = bookToDelete;
        this.listOfBooks = listOfBooks;
    }

    @Override
    public void handleCommand() {
        int inputtedIndex = Integer.parseInt(this.bookToDelete[1]);
        int listNumberIndex = (inputtedIndex - 1);
        System.out.println("Deleted!");
        this.listOfBooks.remove(listNumberIndex);
    }
}
