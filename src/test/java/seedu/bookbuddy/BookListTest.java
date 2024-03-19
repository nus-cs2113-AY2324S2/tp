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

    @Test
    void deleteBook() {
        BookList bookList = new BookList();
        bookList.addBook("Harry Potter");
        assertEquals(1, bookList.getSize());
        bookList.deleteBook(1);
        assertEquals(0, bookList.getSize());
    }

    @Test
    void getBook() {
        BookList bookList = new BookList();
        bookList.addBook("Harry Potter");
        bookList.addBook("Geronimo");
        bookList.addBook("Cradle");
        assertEquals("[U] Cradle", bookList.getBook(2).toString());
    }

    @Test
    void markDoneByIndex() {
        BookList bookList = new BookList();
        bookList.addBook("Harry Potter");
        bookList.markDoneByIndex(1);
        assertEquals("[R] Harry Potter", bookList.getBook(0).toString());
    }

    @Test
    void markUndoneByIndex() {
        BookList bookList = new BookList();
        bookList.addBook("Harry Potter");
        bookList.markDoneByIndex(1);
        assertEquals("[R] Harry Potter", bookList.getBook(0).toString());
        bookList.markUndoneByIndex(1);
        assertEquals("[U] Harry Potter", bookList.getBook(0).toString());
    }

}
