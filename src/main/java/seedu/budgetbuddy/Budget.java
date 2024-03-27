package seedu.budgetbuddy;

public class Budget {
    private String category;
    private double budget;

    public Budget(String category, double budget){
        this.category = category;
        this.budget = budget;
    }

    public String getCategory(){
        return category;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget){
        this.budget = budget;
    }
}
