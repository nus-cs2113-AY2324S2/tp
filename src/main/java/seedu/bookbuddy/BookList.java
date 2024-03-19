package seedu.bookbuddy;

import java.util.ArrayList;

/**
 * Manages a list of books, allowing for operations such as adding, deleting,
 * and marking book as read or unread.
 */
public class BookList {
    private ArrayList<Book> books;

    /**
     * Constructs a new BookList instance with an empty list.
     */
    public BookList() {
        this.books = new ArrayList<Book>(); // Use ArrayList instead of array
    }

    /**
     * Returns the current size of the book list.
     * @return The number of books in the list.
     */
    public int getSize(){
        return books.size();
    }

    /**
     * Retrieves a book from the list based on its index.
     * @param index The index of the book to retrieve.
     * @return The Book at the specified index.
     */
    public Book getBook(int index){
        return books.get(index);
    }

    /**
     * Adds a new Book to the list.
     * @param taskDescription The description of the book.
     */
    public void addBook(String taskDescription) {
        books.add(new Book(taskDescription));
    }

    /**
     * Deletes a book from the list by its index.
     * @param index The index of the book to delete.
     */
    public void deleteBook(int index) {
        books.remove(index-1);
    }

    /**
     * Marks a book as read by its index.
     * @param index The index of the book to mark as read.
     */
    public void markDoneByIndex(int index) {
        books.get(index-1).markBookAsRead();
    }

    /**
     * Marks a book as unread by its index.
     * @param index The index of the book to mark as unread.
     */
    public void markUndoneByIndex(int index) {
        books.get(index-1).markBookAsUnread();
    }

    /**
     * Prints all books currently in the list.
     */
    public void printAllBooks() {
        if (!books.isEmpty()) {
            System.out.println("All books:");
            for (int i = 0; i < books.size(); i++) {
                Book currentBook = books.get(i);
                System.out.print((i + 1) + ".");
                System.out.println(currentBook);
            }
        }
        else {
            System.out.println("The list is empty.");
        }
    }
}
