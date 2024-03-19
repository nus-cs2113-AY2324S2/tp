package bookmarked.command;

import bookmarked.Book;

import java.util.ArrayList;

public class AddCommand extends Command {
    private String newItem;
    private ArrayList<Book> listOfBooks;
    private String[] splitItem;
    public AddCommand(String newItem, ArrayList<Book> listOfBooks, String[] splitItem){
        this.newItem = newItem;
        this.listOfBooks = listOfBooks;
        this.splitItem = splitItem;
    }

    @Override
    public void handleCommand() {
        String[] newSplitBook = this.newItem.split("add");
        this.listOfBooks.add(new Book(splitItem[1]));
        System.out.println("Added!");
    }
}
