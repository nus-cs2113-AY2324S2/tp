package bookmarked;

import bookmarked.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {
    @Test
    public void getBorrowedStatus_unBorrowed_returnsSpace() {
        Book testBook = new Book("Test Book");
        assertEquals(" ", testBook.getBorrowedStatus());
    }

    @Test
    public void getBorrowedStatus_borrowed_returnFormattedString() {
        Book testBook = new Book("Test Book");
        testBook.isBorrowed = true;
        assertEquals(", borrowed", testBook.getBorrowedStatus());
    }

    @Test
    public void toString_descriptionAndBorrowed_returnFormattedString() {
        Book testBook = new Book("The Book Thief");
        testBook.isBorrowed = true;
        assertEquals("The Book Thief, borrowed", testBook.toString());
    }

    @Test
    public void toString_descriptionAndReturned_returnNonFormattedString() {
        Book testBook = new Book("The Book Thief");

        // Borrow the book
        testBook.isBorrowed = true;
        assertEquals("The Book Thief, borrowed", testBook.toString()); // Check the borrowed status

        // Return the book
        testBook.isBorrowed = false;
        assertEquals("The Book Thief ", testBook.toString()); // Check the returned status
    }

}
