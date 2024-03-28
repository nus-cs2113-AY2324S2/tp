package seedu.bookbuddy;

import static seedu.bookbuddy.BookList.books;

public class BookDetails {
    /**
     * Sets the label of the book at the specified index.
     *
     * @param index The index of the book in the list.
     * @param label The label to set for the book.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public static void setBookLabelByIndex(int index, String label) throws IndexOutOfBoundsException {
        // Check for valid index
        if (index < 0 || index >= books.size()) {
            throw new IndexOutOfBoundsException("Invalid book index. Please enter a valid index.");
        }

        // Set the label for the book at the specified index
        books.get(index).setLabel(label);
        String title = books.get(index).getTitle();
        Ui.labelBookMessage(title, label);
    }

    /**
     * Sets the genre of the book at the specified index.
     *
     * @param index The index of the book in the list.
     * @param genre The genre to set for the book.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public static void setBookGenreByIndex(int index, String genre) throws IndexOutOfBoundsException {
        // Check for valid index
        if (index < 0 || index >= books.size()) {
            throw new IndexOutOfBoundsException("Invalid book index. Please enter a valid index.");
        }

        // Set the genre for the book at the specified index
        books.get(index).setGenre(genre);
        String title = books.get(index).getTitle();
        Ui.setGenreBookMessage(title, genre);
    }

}
