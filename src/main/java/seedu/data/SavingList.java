package seedu.data;

import java.util.ArrayList;

import seedu.task.Saving;

public class SavingList {
    protected ArrayList <Saving> savings;
    protected ArrayList<String> categories;

    public SavingList() {
        this.savings = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public void addSaving(String category, String amount) {
        int amountInt = Integer.parseInt(amount);
        Saving saving = new Saving(category, amountInt);
        savings.add(saving);

        if (!categories.contains(category)) {
            categories.add(category);
        }
    }
}
