package item;

import itemlist.Itemlist;

public class Item {
    public static int numberOfItems;
    private final String itemName;
    private int quantity;
    private String uom;
    private String category;
    private boolean isOOS;


    public Item(String name, int quantity, String uom, String category) {
        this.itemName = name;
        this.quantity = quantity;
        this.uom = uom;
        if (category.isEmpty()) {
            this.category = "NA";
        } else {
            this.category = category;
        }
        if (quantity == 0) {
            this.isOOS = true;
        } else {
            this.isOOS = false;
        }
        numberOfItems++;
    }

    public String getCategory() {
        if (this.category.equals("NA")) {
            return null;
        } else {
            return this.category;
        }
    }
    public String getItemName() {
        return this.itemName;
    }

    public String getUom() {
        return uom;
    }
    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public void markOOS() {
        this.isOOS = true;
    }

    public void unmarkOOS() {
        this.isOOS = false;
    }

    public String toString() {
        String categoryString = (getCategory() != null) ? " to " + getCategory() : ""; // Check if category is null
        return (getItemName() + " (Qty " + getQuantity() + " " + getUom() + ")" + categoryString);
    }
}
