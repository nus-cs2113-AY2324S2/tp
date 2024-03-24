package supertracker.item;

import  java.time.LocalDate;

public class Item {
    private String name;
    private int quantity;
    private double price;
    private LocalDate expiryDate;

    public Item(String name, int quantity, double price, LocalDate expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public String getPriceString() {
        return "$" + String.format("%.2f", price);
    }
}
