package seedu.bookbuddy;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    void testParser() {
        BookList books = new BookList();
        books.addBook("Don Quixote");
        books.addBook("Gulliver's Travels");
        assertEquals(2, books.getSize());
        books.markDoneByIndex(1);
        assertEquals("[R] Don Quixote", books.getBook(0).toString());
        assertEquals("[U] Gulliver's Travels", books.getBook(1).toString());
        books.deleteBook(1);
        books.markDoneByIndex(1);
        assertTrue(books.getBook(0).isRead);
        assertEquals("[R] Gulliver's Travels", books.getBook(0).toString());
    }

    @Test
    void parseAddCommand() {
        BookList testBookList = new BookList();
        Parser.parseCommand("add The Great Gatsby", testBookList);
        assertEquals(1, testBookList.getSize());
        assertEquals("The Great Gatsby", testBookList.getBook(0).getTitle());
    }

    @Test
    void parseRemoveCommand() {
        BookList books = new BookList();
        books.addBook("The Great Gatsby");
        Parser.parseCommand("remove 1", books);
        assertEquals(0, books.getSize());
    }

    @Test
    void parseMarkCommand() {
        BookList books = new BookList();
        books.addBook("The Great Gatsby");
        Parser.parseCommand("mark 1", books);
        assertTrue(books.getBook(0).isRead());
    }

    @Test
    void parseUnmarkCommand() {
        BookList books = new BookList();
        books.addBook("The Great Gatsby");
        Parser.parseCommand("mark 1", books);
        Parser.parseCommand("unmark 1", books);
        assertFalse(books.getBook(0).isRead());
    }

    @Test
    void parseInvalidCommand() {
        BookList books = new BookList();
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        Parser.parseCommand("invalid", books);
        assertEquals("Sorry but that is not a valid command. Please try again", outContent.toString());
        System.setOut(originalOut);
    }


}
