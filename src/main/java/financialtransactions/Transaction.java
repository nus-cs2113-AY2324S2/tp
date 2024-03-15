package financialtransactions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import template.BaseDate;

public abstract class Transaction<T> {
    protected String name;
    protected double amount;
    protected BaseDate date;
    protected T category;

    public Transaction(String name, double amount, String date) {
        this.name = name;
        this.amount = amount;
        if(date == null){
            this.date = new BaseDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        } else{
            this.date = new BaseDate(date);
        }
    }

    protected String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public T getCategory() {
        return category;
    }

    protected abstract void setCategory(T category);

    @Override
    public String toString() {
        String baseString = String.format("Name: %s, Amount: %.2f, Date: %s", name, amount, date.toString());
        return baseString;
    }
    
    public String toSave() {
        String baseString = String.format("%s|%.2f|%s|%s\n", name, amount, date.toString(), category);
        return baseString;
    }
}
