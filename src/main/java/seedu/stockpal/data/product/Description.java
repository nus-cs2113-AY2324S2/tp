package seedu.stockpal.data.product;

public class Description {
    private static final String EMPTY_STRING = "";
    protected String description;

    public Description(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isNull() {
        return this.description == null || this.description.isEmpty();
    }

    @Override
    public String toString() {
        return "Description: " + (this.isNull()
                ? "[X]"
                : description);
    }

    /**
     * Converts the Description to the specific format for saving to the data file.
     *
     * @return A formatted string containing the Description for saving.
     */
    public String toSave() {
        return this.isNull()
                ? EMPTY_STRING
                : this.description;
    }
}
