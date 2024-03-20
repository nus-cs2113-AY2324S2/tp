package item;

public class Item {
    public static int numberOfItems;
    private final String itemName;
    private int quantity;
    private String uOM;

    public Item(String name, int quantity, String uom) {
        this.itemName = name;
        this.quantity = quantity;
        this.uOM = uom;
        numberOfItems++;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
}
