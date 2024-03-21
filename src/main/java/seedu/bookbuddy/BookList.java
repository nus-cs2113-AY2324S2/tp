package seedu.bookbuddy;


import exceptions.BookNotFoundException;
import exceptions.BookReadAlreadyException;
import exceptions.BookUnreadAlreadyException;

import java.util.ArrayList;

/**
 * Manages a list of books, allowing for operations such as adding, deleting,
 * and marking book as read or unread.
 */
public class BookList {
    protected static ArrayList<Book> books;

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
    public Book getBook(int index) throws BookNotFoundException{
        if (index < 0 || index > books.size()) {
            throw new BookNotFoundException("Book index out of range.");
        }
        assert books.get(index) != null : "Retrieved book should not be null";
        return books.get(index);
    }

    /**
     * Adds a new Book to the list.
     * @param title The title of the book.
     */
    public void addBook(String title) {
        books.add(new Book(title));
        Ui.addBookMessage(title);
        assert !books.isEmpty() : "Book list should not be empty after adding a book";
    }

    /**
     * Deletes a book from the list by its index.
     * @param index The index of the book to delete.
     */
    public void deleteBook(int index) throws IndexOutOfBoundsException {
        try {
            Ui.removeBookMessage(index - 1);
            books.remove(index - 1);
            assert books.size() >= 0 : "Book list size should not be negative after deletion";
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid book index. Please enter a valid index");
        }
    }

    /**
     * Marks a book as read by its index.
     * @param index The index of the book to mark as read.
     */
    public void markDoneByIndex(int index) throws IndexOutOfBoundsException, BookReadAlreadyException{
        try {
            assert index > 0 && index <= books.size() : "Index out of valid range";
            if (books.get(index - 1).isRead()) {
                throw new BookReadAlreadyException("That book is already marked as read!");
            }
            assert !books.get(index - 1).isRead() : "Book is already marked as read";
            books.get(index - 1).markBookAsRead();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid book index. Please enter a valid index");
        } catch (BookReadAlreadyException e) {
            System.out.println("That book is already marked as read!");
        }
    }

    /**
     * Marks a book as unread by its index.
     * @param index The index of the book to mark as unread.
     */
    public void markUndoneByIndex(int index) throws IndexOutOfBoundsException, BookReadAlreadyException{
        try {
            assert index > 0 && index <= books.size() : "Index out of valid range";
            if (!books.get(index - 1).isRead()) {
                throw new BookUnreadAlreadyException("That book is already marked as unread!");
            }
            assert books.get(index - 1).isRead() : "Book is already marked as unread";
            books.get(index - 1).markBookAsUnread();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid book index. Please enter a valid index");
        } catch (BookUnreadAlreadyException e) {
            System.out.println("That book is already marked as unread!");
        }
    }

    /**
     * Prints all books currently in the list.
     */
    public static void printAllBooks() {
        if (!BookList.books.isEmpty()) {
            System.out.println("All books:");
            for (int i = 0; i < BookList.books.size(); i++) {
                Book currentBook = BookList.books.get(i);
                System.out.print((i + 1) + ". ");
                System.out.println(currentBook.toString());
            }
        } else {
            System.out.println("The list is empty.");
        }
    }
}
