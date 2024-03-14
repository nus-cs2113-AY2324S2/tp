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
        this.orderID = "ORDER" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    @Override
    public void add(MenuItem item) {
        this.orderItemList.add(item);
    }

    /**
     * Removes an item from the order list by its index
     * @param index the index of the item to be removed
     */
    @Override
    public void remove(int index) {
        try {
            this.orderItemList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        }
    }

    /**
     * Removes all items from the order list by its id
     * @param id the id of the item to be removed
     */
    @Override
    public void remove(String id) {
        this.orderItemList.removeIf(x -> x.getID().equals(id));
    }

    public String getID() {
        return orderID;
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

    @Override
    public String toString() {
        return this.orderID + "\n" +
                IntStream.range(0, this.orderItemList.size())
                        .mapToObj(x -> (x + 1) + ". " + this.orderItemList.get(x))
                        .collect(Collectors.joining("\n"));
    }
}
