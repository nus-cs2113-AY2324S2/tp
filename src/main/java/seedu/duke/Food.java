package seedu.duke;

public class Food extends Favourites {
    public Food(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[F]" + super.toString();
    }

    public String toFileFormat() {
        return "F | " + description;
    }

}
