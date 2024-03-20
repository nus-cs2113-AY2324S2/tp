package item;
import constants.UOM;

public class Item {
    public static int numberOfItems;
    protected final String itemName;
    protected int quantity;
    protected UOM uom;
    protected int uomQty;
    protected float costPrice;
    protected float salePrice;


    public Item(String name, int quantity, UOM uom, int uomQty) {
        this.itemName = name;
        this.quantity = quantity;
        this.uom = uom;
        this.uomQty = uomQty;
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
    public UOM getUom() {
        return this.uom;
    }
    public void setUomQty(int newQty) {
        this.uomQty = newQty;
    }
}
