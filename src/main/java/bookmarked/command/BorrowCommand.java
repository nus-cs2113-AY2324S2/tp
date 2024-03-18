package bookmarked.command;

import bookmarked.Book;

import java.util.ArrayList;

public class BorrowCommand extends Command {
    String[] bookToBorrow;
    ArrayList<Book> listOfBooks;
    public BorrowCommand(String[] bookToBorrow, ArrayList<Book> listOfBooks) {
        this.bookToBorrow = bookToBorrow;
        this.listOfBooks = listOfBooks;
    }

    @Override
    public void handleCommand() {
        int inputtedIndex = Integer.parseInt(this.bookToBorrow[1]);
        int listNumberIndex = (inputtedIndex - 1);
        System.out.println("Borrowed!");
        this.listOfBooks.get(listNumberIndex).setBorrowed();
    }
}
