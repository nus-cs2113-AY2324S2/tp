package model;

public class MenuItem extends Item{
    public MenuItem(String ID, String name, double unitPrice) {
        super(ID, name, unitPrice);
    }

    @Override
    public String toString() {
        return String.format("%s %s $%.2f", this.getID(), this.getName(), this.getPrice());
    }

}
