package seedu.lifetrack.calories.calorielist;

public abstract class Entry {

    private String description;
    private int calories;
    private String date;

    public Entry(String description, int calories, String date){
        this.description = description;
        this.calories = calories;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public String getDate() {
        return date;
    }

    public String toString() {
        return String.format("\t Date: " + date + ", Description: " + description + ", Calories: " + calories);
    }
}
