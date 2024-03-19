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
        BookBuddy.addBook("addBook Harry Potter");
        assertEquals(1, BookBuddy.bookDetailsList.size());
        assertEquals("Harry Potter", BookBuddy.bookDetailsList.get(0).getDescription());
    }

}
