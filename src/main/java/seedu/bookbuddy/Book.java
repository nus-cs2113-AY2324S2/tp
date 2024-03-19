package seedu.bookbuddy;

public class Book {

    public String description;
    protected boolean isRead;


    /**
     * Creates a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Book(String description) {
        this.description = description; // Description of the task
        this.isRead = false; //Completion status of the task(True: Read, False: Unread)
    }

    /**
     * Returns the description of the book.
     *
     * @return The description of the book.
     */
    public String getDescription() {
        return this.description;
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
    }

    /**
     * Marks the book as unread.
     */
    public void markBookAsUnread() {
        this.isRead = false;
    }

    public String toString() {
        String statusMark = this.isRead() ? "X" : " "; // Mark with 'x' if completed
        return  this.description + " [" + statusMark + "]" ;
    }
}
