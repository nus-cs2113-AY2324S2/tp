package bookmarked.command;

import bookmarked.Book;

import java.util.ArrayList;

public class ReturnCommand extends Command {
    String[] bookToReturn;
    ArrayList<Book> listOfBooks;
    public ReturnCommand(String[] bookToReturn, ArrayList<Book> listOfBooks) {
        this.bookToReturn = bookToReturn;
        this.listOfBooks = listOfBooks;
    }

    @Override
    public void handleCommand() {
        int inputtedIndex = Integer.parseInt(bookToReturn[1]);
        int listNumberIndex = (inputtedIndex - 1);
        System.out.println("Returned!");
        listOfBooks.get(listNumberIndex).setReturned();
    }
}
