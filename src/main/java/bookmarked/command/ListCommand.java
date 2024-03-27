package bookmarked.command;

import bookmarked.Book;
import bookmarked.exceptions.EmptyListException;
import bookmarked.ui.Ui;

import java.util.ArrayList;
import java.util.Comparator;

public class ListCommand extends Command {
    private ArrayList<Book> listOfBooks;
    private String inputCommand;
    private ArrayList<Book> sortedListOfBooks;
    private String[] splitCommand;
    int numberOfBooks;


    public ListCommand(ArrayList<Book> listOfBooks, String newItem) {
        this.listOfBooks = listOfBooks;
        this.inputCommand = newItem;
        this.numberOfBooks = listOfBooks.size();
    }

    @Override
    public void handleCommand() {
        this.splitCommand = inputCommand.split("list ");

        //This will be updated to switch/case when the date feature is added
        if (inputCommand.matches("list")) {
            try {
                runListBlankCommand();
            } catch (EmptyListException e) {
                Ui.printEmptyListMessage();
            }
        } else if (splitCommand[1].equals("alphabetical")) {
            try {
                runListAlphabeticalCommand();
            } catch (EmptyListException e) {
                Ui.printEmptyListMessage();
            }
        } else {
            System.out.println("error, the list function went wrong somewhere.");
        }

    }


    public void runListBlankCommand() throws EmptyListException {
        if (this.listOfBooks.isEmpty()) {
            throw new EmptyListException();
        }

        System.out.println("Here are all the books currently in the library's inventory!");
        for (int i = 0; i < numberOfBooks; i++) {
            System.out.println((i + 1) + ". " + this.listOfBooks.get(i).toString());
        }
    }


    public void runListAlphabeticalCommand() throws EmptyListException {
        if (this.listOfBooks.isEmpty()) {
            throw new EmptyListException();
        }

        System.out.println("Here are the list of all current books that are currently in\n" +
                "the library's inventory listed by alphabetical order!\n");
        this.sortedListOfBooks = new ArrayList<>(this.listOfBooks);
        this.sortedListOfBooks.sort(new Comparator<Book>() {
            @Override
            public int compare(Book book_One, Book book_Two) {
                return book_One.getName().compareTo(book_Two.getName());
            }
        });

        for (int i = 0; i < numberOfBooks; i++) {
            System.out.println((i + 1) + ". " + this.sortedListOfBooks.get(i).toString());
        }
    }

}
