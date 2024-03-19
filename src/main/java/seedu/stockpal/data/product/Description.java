package seedu.stockpal.data.product;

import org.apache.commons.text.WordUtils;
import seedu.stockpal.common.CommandParameter;

public class Description implements CommandParameter {
    private static final String EMPTY_STRING = "";
    private static final int WRAP_LENGTH = 70;
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

    public String toString () {
        if (this.isNull()) {
            return "Description: [X]";
        }

        String textToFormat = "Description: " + description;
        return WordUtils.wrap(textToFormat, WRAP_LENGTH, System.lineSeparator(), true);
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
