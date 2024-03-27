package seedu.stockpal.data.product;

import seedu.stockpal.common.CommandParameter;
import seedu.stockpal.ui.Ui;

public class Description implements CommandParameter {
    private static final String EMPTY_STRING = "";
    private final String description;

    public Description(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isNull() {
        return this.description == null || this.description.isEmpty();
    }

    /**
     * Formats the description of the product accordingly.
     * Wraps the description around if it's length exceeds 70 characters.
     *
     * @return a formatted string that shows it's an optional field or is indented accordingly.
     */
    @Override
    public String toString () {
        if (this.isNull()) {
            return "Description: [X]";
        }

        String textToFormat = "Description: " + description;
        return Ui.indentTextIfRequired(textToFormat);
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
