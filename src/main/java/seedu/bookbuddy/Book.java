package seedu.bookbuddy;

public class Book {

    public String title;
    protected boolean isRead;


    /**
     * Creates a new Book with the specified title.
     *
     * @param title The description of the book.
     */
    public Book(String title) {
        this.title = title; // Description of the book
        this.isRead = false; //Completion status of the book (True: Read, False: Unread)
    }

    /**
     * Returns the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Checks if the book is read.
     *
     * @return True if the book is read, false otherwise.
     */
    public boolean isRead() {
        return this.isRead;
    }

    /**
     * Marks the book as read.
     */
    public void markBookAsRead() {
        this.isRead = true;
        System.out.println("Successfully marked " + this.getTitle() + " as read.");
    }

    /**
     * Marks the book as unread.
     */
    public void markBookAsUnread() {
        this.isRead = false;
        System.out.println("Successfully marked " + this.getTitle() + " as unread.");
    }

    public String toString() {
        String statusMark = this.isRead() ? "R" : "U"; // Mark with 'R' if read and 'U' if unread
        return "[" + statusMark + "] " + this.title;
    }
}
