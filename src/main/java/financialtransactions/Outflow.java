package financialtransactions;

public class Outflow extends Transaction<Outflow.Category> {
    public enum Category {
        RENT, DEBT, SHOPPING, TREAT, EDUCATION, OTHER
    }

    public Outflow(String name, double amount, String date) {
        super(name, -1.00 * amount, date);
        super.transactionType = "O";
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
