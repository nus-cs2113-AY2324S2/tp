package seedu.bookbuddy;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
