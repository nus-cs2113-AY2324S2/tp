package itemlist;
import item.Item;

import java.util.ArrayList;

public class Itemlist {
    private static final ArrayList<Item> items = new ArrayList<>();

    public Itemlist() {
    }

    public static void addItem(Item item) {
        items.add(item);
    }

    public static void deleteItem(int index) {
        items.remove(index);
    }

    public static void editQuantity(int index, int newQuantity) {
        items.get(index).setQuantity(newQuantity);
        if (newQuantity == 0) {
            items.get(index).markOOS();
        } else if (newQuantity > 1) {
            items.get(index).unmarkOOS();
        }
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static int getIndex(Item item) {
        return items.indexOf(item);
    }
}
