package bookmarked;

public class Book {
    protected String description;
    protected boolean isBorrowed;

    public Book(String description) {
        this.description = description;
        this.isBorrowed = false;
    }

    public String getName() {
        return this.description;
    }
    public String getBorrowedStatus() {
        return (isBorrowed ? ", borrowed" : " "); // mark done task with X
    }

    public void setBorrowed(boolean status) {
        this.isBorrowed = true;
    }

    public void setReturned() {
        this.isBorrowed = false;
    }

    public String getBookDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return (this.description + getBorrowedStatus());
    }

}




