package supertracker.item;

import java.util.Comparator;

public class Item {
    private String name;
    private int quantity;
    private double price;

    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
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

    public String getPriceString() {
        return "$" + String.format("%.2f", price);
    }

    public static Comparator<Item> sortByName() {
        return Comparator.comparing(Item::getName);
    }

    public static Comparator<Item> sortByQuantity() {
        return Comparator.comparingInt(Item::getQuantity);
    }

    public static Comparator<Item> sortByPrice() {
        return Comparator.comparingDouble(Item::getPrice);
    }
}
