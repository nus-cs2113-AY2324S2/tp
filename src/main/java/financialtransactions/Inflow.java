package financialtransactions;
import financialtransactions.Transaction;

public class Inflow extends Transaction<Inflow.Category> {
    protected enum Category {
        INCOME, INVESTMENT, GIFT, LOAN, REFUND, OTHER
    }
    public Inflow(String name, double amount) {
        super(name, amount);
    }
    protected void setCategory(Category category) {
        super.category = category;
    }
}
