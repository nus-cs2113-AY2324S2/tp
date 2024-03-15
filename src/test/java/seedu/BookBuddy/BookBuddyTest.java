package seedu.BookBuddy;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

class BookBuddyTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    void main() {
    }

    @Test
    void addBook() {
        BookBuddy.addBook("addBook Harry Potter");
        assertEquals(1, BookBuddy.bookDetailsList.size());
        assertEquals("Harry Potter", BookBuddy.bookDetailsList.get(0).getDescription());
    }

    @Test
    void printList() {
    }
}
