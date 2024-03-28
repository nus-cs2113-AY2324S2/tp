package item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String dateTime;
    private int totalPrice;
    private int profit;
    private String itemName;
    private int quantity;
    private int buyPrice;
    private int sellPrice;

    public Transaction(String name, int inputQty, int inputBuy, int inputSell) {
        setDateTime();
        itemName = name;
        quantity = inputQty;
        buyPrice = inputBuy;
        sellPrice = inputSell;
        totalPrice = sellPrice * quantity;
        profit = totalPrice - buyPrice * quantity;
    }

    public Transaction(String name, int inputQty, int inputBuy, int inputSell, String storedTime) {
        dateTime = storedTime;
        itemName = name;
        quantity = inputQty;
        buyPrice = inputBuy;
        sellPrice = inputSell;
        totalPrice = sellPrice * quantity;
        profit = totalPrice - buyPrice * quantity;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getSellPrice() {
        return this.sellPrice;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public int getTotalPrice() {
        return this.totalPrice;
    }

    public int getProfit() {
        return this.profit;
    }

    public void setDateTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateTime = currentTime.format(formatter);
    }
}
