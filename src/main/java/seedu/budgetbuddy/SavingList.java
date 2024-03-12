package seedu.budgetbuddy;

import java.util.ArrayList;
import java.util.Arrays;

public class SavingList {
    protected ArrayList <Saving> savings;
    protected ArrayList<String> categories;

    public SavingList() {
        this.savings = new ArrayList<>();
        this.categories = new ArrayList<>(Arrays.asList("Salary", 
        "Investments", "Gifts", "Others"));
    }

    public void addSaving(String category, String amount) {
        int amountInt = Integer.parseInt(amount);
        Saving saving = new Saving(category, amountInt);
        savings.add(saving);

        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    public void editSaving(String category, int index, double amount) {
        int categoryIndex = categories.indexOf(category);
        if (categoryIndex != -1 && index > 0 && index <= savings.size()) {
            Saving savingToEdit = savings.get(index - 1);
            savingToEdit.setCategory(category);
            savingToEdit.setAmount(amount);
            System.out.println("Saving edited successfully.");
        } else {
            System.out.println("Invalid category or index.");
        }
    }

    public void reduceSavings(int index, double amount){
        if (index >= 0 && index < savings.size()){
            Saving saving = savings.get(index);
            if(saving.getAmount() >= amount){
                saving.setAmount(saving.getAmount() - amount);
            } else {
                System.out.println("Insufficient savings amount.");
            }
        } else {
            System.out.println("Invalid saving index.");
        }
    }

}
