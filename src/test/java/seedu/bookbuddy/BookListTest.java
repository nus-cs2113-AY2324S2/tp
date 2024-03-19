package seedu.bookbuddy;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BookListTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }


    @Test
    void addBook() {
        BookList bookList = new BookList();
        bookList.addBook("Harry Potter");
        assertEquals(1, bookList.getSize());
        assertEquals("Harry Potter [ ]", bookList.getBook(0).toString());
    }

}
