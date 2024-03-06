package financialtransactions;
import financialtransactions.Transaction;

public class Outflow extends Transaction<Outflow.Category> {
    protected enum Category {
        RENT, DEBT, SHOPPING, TREAT, EDUCATION, OTHER
    }
    public Outflow(String name, double amount) {
        super(name, amount);
    }
    protected void setCategory(Category category) {
        this.category = category;
    }
}
