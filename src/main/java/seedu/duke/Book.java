package seedu.duke;

public class Book {
    protected String description;
    protected boolean isBorrowed;

    public Book(String description) {
        this.description = description;
        this.isBorrowed = false;
    }

    public String getBorrowedStatus() {
        return (isBorrowed ? ", borrowed" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return (this.description + getBorrowedStatus());
    }

}

