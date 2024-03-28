package bookmarked.command;

import bookmarked.Book;
import bookmarked.exceptions.EmptyListException;
import bookmarked.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FindCommand extends Command {
    public static final int FIND_KEYWORD_START_INDEX = 5;
    private static Logger logger = Logger.getLogger("Find Command Logger");
    private String newItem;
    private ArrayList<Book> listOfBooks;
    public FindCommand(String newItem, ArrayList<Book> listOfBooks) {
        this.newItem = newItem;
        this.listOfBooks = listOfBooks;
    }

    @Override
    public void handleCommand() {
        assert listOfBooks != null : "list of books should not be empty";
        String keyword;
        logger.log(Level.INFO, "going to start processing find command");
        try {
            keyword = this.newItem.substring(FIND_KEYWORD_START_INDEX);
            if (keyword.isBlank()) {
                throw new StringIndexOutOfBoundsException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "processing error for empty keyword");
            System.out.println("Find keyword cannot be empty!");
            return;
        }

        try {
            processFind(keyword);
        } catch (EmptyListException e) {
            Ui.printEmptyListMessage();
        }
    }

    private void processFind(String keyword) throws EmptyListException {
        if (this.listOfBooks.isEmpty()) {
            throw new EmptyListException();
        }

        logger.log(Level.INFO, "processing find books based on keyword");
        assert keyword != null : "keyword should not be empty";
        int numberOfBookFound = 0;
        ArrayList<Book> bookFound = new ArrayList<>();

        // filter books based on keyword
        for (Book currentBook : this.listOfBooks) {
            if (!(currentBook.getName().contains(keyword))) {
                continue;
            }
            assert currentBook.getName().contains(keyword) : "current book should contain the keyword";
            numberOfBookFound =+ 1;
            bookFound.add(currentBook);
        }

        if (numberOfBookFound == 0) {
            logger.log(Level.INFO, "giving no matching book found warning");
            System.out.println("Sorry, no book with matching keyword: " + keyword);
            return;
        }

        logger.log(Level.INFO, "processing print of matching book lists");
        System.out.println("Here's the list of matching books in your library:");
        for (int i = 0; i < bookFound.size(); i += 1) {
            String currentBookTitle = bookFound.get(i).getName();
            System.out.println(" " + (i + 1) + ". " + currentBookTitle);
        }
        logger.log(Level.INFO, "end processing");
    }
}
