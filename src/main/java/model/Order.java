package model;


import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order implements ItemManager {
    private static final double SERVICE_CHARGE = 0.1;
    private static final double GST = 0.09;
    private final String orderID;
    private final ArrayList<MenuItem> orderItemList = new ArrayList<>();

    public Order() {
        this.orderID = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    @Override
    public boolean add(MenuItem item) {
        this.orderItemList.add(item);
        return true;
    }

    /**
     * Removes all items from the order list by its id
     * @param itemID the id of the item to be removed
     */
    @Override
    public boolean remove(String itemID) {
        return this.orderItemList.removeIf(x -> x.getID().equals(itemID));
    }

    public boolean remove(MenuItem item) {
        return this.orderItemList.remove(item);
    }

    public int getItemCount(String itemID) {
        return (int) this.orderItemList.stream().filter(x -> x.getID().equals(itemID)).count();
    }

    public String getID() {
        return orderID;
    }

    public int getSize() {
        return this.orderItemList.size();
    }

    public double getTotalPrice() {
        return Double.parseDouble(String.format("%.2f",
                this.orderItemList.stream().mapToDouble(Item::getPrice).sum() * (1+SERVICE_CHARGE+GST)));
    }

    //TODO: Implement getReceipt method
    public String getReceipt() {
        return null;
    }

    //TODO: Implement getReceipt method with discount
    public String getReceipt(double discount) {
        return null;
    }

    /**
     * Returns a brief summary of the order
     * @return the orderID and the total price of the order
     */
    public String getOrderSummary() {
        return this.orderID + " {Total Price: " + this.getTotalPrice() + "}";
    }

    @Override
    public String toString() {
        return this.orderID + "\n" +
                IntStream.range(0, this.orderItemList.size())
                        .mapToObj(x -> (x + 1) + ". " + this.orderItemList.get(x))
                        .collect(Collectors.joining("\n"));
    }
}
