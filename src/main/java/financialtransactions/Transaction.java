package financialtransactions;

public abstract class Transaction<T> {
    protected String name;
    protected double amount;
    protected String date;
    protected T category;

    public Transaction(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }
    protected String getName() {
        return name;
    }
    protected double getAmount() {
        return amount;
    }
    protected T getCategory() {
        return category;
    }
    protected abstract void setCategory(T category);
}