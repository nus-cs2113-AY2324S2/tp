package model;


public class MenuItem extends Item implements Comparable<MenuItem>{
    public MenuItem(String id, String name, double unitPrice) {
        super(id, name, unitPrice);
    }

    @Override
    public String toString() {
        return String.format("%s %s $%.2f", this.getID(), this.getName(), this.getPrice());
    }

    @Override
    public int compareTo(MenuItem o) {
        return this.getID().compareTo(o.getID());
    }
}
