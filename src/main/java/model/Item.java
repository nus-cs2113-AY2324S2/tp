package model;

public abstract class Item {
    private final String ID;
    private final String name;
    private final double unitPrice;

    public Item(String ID, String name, double unitPrice) {
        this.ID = ID;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.unitPrice;
    }
}
