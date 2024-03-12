package seedu.duke;
public abstract class Transaction {

    protected String category;
    protected double amount;
    protected String description;

    public Transaction(String category, double amount, String description){
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCategory(){
        this.category = category;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
