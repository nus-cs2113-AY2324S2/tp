package seedu.duke;

public class Activity extends Favourites{
    public Activity(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[A] " + super.toString();
    }

    public String toFileFormat() {
        return "A | " + description;
    }
}
