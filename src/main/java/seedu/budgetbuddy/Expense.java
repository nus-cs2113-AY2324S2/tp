package seedu.budgetbuddy;

public class Expense extends Transaction{
    protected String description;
    
    public Expense(String category, int amount, String description) {
        super(category, amount);
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category: " + category + " Amount: " + amount + " Description: " + description;
    }
}
