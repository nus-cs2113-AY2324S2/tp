package seedu.duke;
import java.util.ArrayList;

class SavingList {
    protected ArrayList<Saving> savings;
    protected ArrayList<String> categories;

    public SavingList() {
        this.savings = new ArrayList<>();
        this.categories = new ArrayList<>();
        // Hardcode saving categories
        categories.add("Salary");
        categories.add("Investments");
        categories.add("Gifts");
        categories.add("Others");
    }

    // Method to edit saving
    public void editSaving(String category, int index, double amount, String description) {
        int categoryIndex = categories.indexOf(category);
        if (categoryIndex != -1 && index > 0 && index <= savings.size()) {
            Saving savingToEdit = savings.get(index - 1);
            savingToEdit.setCategory(category);
            savingToEdit.setAmount(amount);
            savingToEdit.setDescription(description);
            System.out.println("Saving edited successfully.");
        } else {
            System.out.println("Invalid category or index.");
        }
    }
}
