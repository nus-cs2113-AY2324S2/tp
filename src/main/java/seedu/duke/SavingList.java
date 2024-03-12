package seedu.duke;

import java.util.ArrayList;

public class SavingList {
    protected ArrayList<Saving> savings = new ArrayList<>();
    protected ArrayList<String> categories = new ArrayList<>();

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
