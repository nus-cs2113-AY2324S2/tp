package seedu.bookbuddy;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


class BookListTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }


    @Test
    void addBook() {
        BookList testBookList = new BookList();
        testBookList.addBook("Harry Potter");
        assertEquals(1, testBookList.getSize());
        assertEquals("[U] Harry Potter", testBookList.getBook(0).toString());
    }


    @Test
    void deleteBook() {
        BookList testBookList = new BookList();
        testBookList.addBook("Harry Potter");
        testBookList.addBook("The Hobbit");
        assertEquals(2, testBookList.getSize());

        testBookList.deleteBook(1); // Delete the first book
        assertEquals(1, testBookList.getSize());
        assertEquals("The Hobbit", testBookList.getBook(0).getTitle());
    }

    @Test
    void markDoneAndUndoneByIndex() {
        BookList testBookList = new BookList();
        testBookList.addBook("Harry Potter");

        testBookList.markDoneByIndex(1); // Mark the first book as read
        assertTrue(testBookList.getBook(0).isRead());

        testBookList.markUndoneByIndex(1); // Mark the first book as unread
        assertFalse(testBookList.getBook(0).isRead());
    }



    @Test
    void printAllBooks() {
        BookList testBookList = new BookList();
        testBookList.addBook("Harry Potter");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        testBookList.printAllBooks();

        String expectedOutput = "All books:\n1. [U] Harry Potter\n";
        assertEquals(expectedOutput, outContent.toString());

        System.setOut(System.out);
    }




}
