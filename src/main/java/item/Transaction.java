package item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction extends Item {
    private String dateTime;
    private int totalPrice;
    private int profit;
    public Transaction(String name, int quantity, String uom, String category, int buyPrice, int sellPrice) {
        super(name, quantity, uom, category, buyPrice, sellPrice);
        setDateTime();
        totalPrice = sellPrice * quantity;
        profit = totalPrice - buyPrice * quantity;
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
