package bookmarked.command;

import bookmarked.Book;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final int FIND_KEYWORD_START_INDEX = 5;
    private String newItem;
    private ArrayList<Book> listOfBooks;
    public FindCommand(String newItem, ArrayList<Book> listOfBooks) {
        this.newItem = newItem;
        this.listOfBooks = listOfBooks;
    }

    @Override
    public void handleCommand() {
        String keyword;
        try {
            keyword = this.newItem.substring(FIND_KEYWORD_START_INDEX);
            if (keyword.isBlank()) {
                throw new StringIndexOutOfBoundsException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Find keyword cannot be empty!");
            return;
        }
        processFind(keyword);
    }

    private void processFind(String keyword) {
        assert keyword != null : "keyword should not be empty";
        int numberOfBookFound = 0;
        ArrayList<Book> bookFound = new ArrayList<>();

        // filter books based on keyword
        for (Book currentBook : this.listOfBooks) {
            if (!(currentBook.getBookDescription().contains(keyword))) {
                continue;
            }
            assert currentBook.getBookDescription().contains(keyword) : "current book should contain the keyword";
            numberOfBookFound =+ 1;
            bookFound.add(currentBook);
        }

        if (numberOfBookFound == 0) {
            System.out.println("Sorry, no book with matching keyword: " + keyword);
            return;
        }

        System.out.println("Here's the list of matching books in your library:");
        for (int i = 0; i < bookFound.size(); i += 1) {
            String currentBookTitle = bookFound.get(i).getBookDescription();
            System.out.println(" " + (i + 1) + ". " + currentBookTitle);
        }
    }
}
