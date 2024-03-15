package seedu.BookBuddy;

public class BookDetails {

    public String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public BookDetails(String description) {
        this.description = description;// Description of the task
        this.isDone = false;//Completion status of the task(True: Done, False: Undone)
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return this.description; //combine the status and task description for easier listing
    }
}
