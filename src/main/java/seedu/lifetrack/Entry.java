package seedu.lifetrack;

public abstract class Entry {

    private String description;
    private String date;

    public Entry(String description, String date){
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String toString() {
        return String.format("\t Date: " + date + ", Description: " + description);
    }

    public String toFileFriendlyString() {
        return String.format(date + ";" + description);
    }
}
