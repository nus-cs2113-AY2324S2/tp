package seedu.duke;

public class Favourites {
    protected String description;

    public Favourites(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return description;
    }

    public String toFileFormat() {
        return description;
    }

}
