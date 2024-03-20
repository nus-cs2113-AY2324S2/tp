package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    public void testMenuItemOutput() {
        MenuItem dish01 = new MenuItem("D01", "Chicken Rice", 3.50);
        MenuItem dish02 = new MenuItem("D02", "Nasi Lemak", 3.00);
        MenuItem dish03 = new MenuItem("D03", "Mee Goreng", 4.00);
        MenuItem dish04 = new MenuItem("D04", "Mee Siam", 3.50);
        MenuItem dish05 = new MenuItem("D04", "Mee Siam", 3.50);
        assertEquals("D01 Chicken Rice $3.50", dish01.toString());
        assertEquals("D02 Nasi Lemak $3.00", dish02.toString());
        assertEquals("D03 Mee Goreng $4.00", dish03.toString());
        assertEquals("D04 Mee Siam $3.50", dish04.toString());
        assertEquals("D04 Mee Siam $3.50", dish05.toString());
    }

    @Test
    public void testOrderAddAndRemove() {
        MenuItem dish01 = new MenuItem("D01", "Chicken Rice", 3.50);
        MenuItem dish02 = new MenuItem("D02", "Nasi Lemak", 3.00);
        MenuItem dish04 = new MenuItem("D04", "Mee Siam", 3.50);
        MenuItem dish05 = new MenuItem("D04", "Mee Siam", 3.50);
        Order order = new Order();
        order.add(dish01);
        assertEquals(order.getID() + "\n" +
                "1. D01 Chicken Rice $3.50", order.toString());
        order.add(dish02);
        assertEquals(order.getID() + "\n" +
                "1. D01 Chicken Rice $3.50" + "\n" +
                "2. D02 Nasi Lemak $3.00", order.toString());
        order.add(dish04);
        order.add(dish05);
        assertEquals(order.getID() + "\n" +
                "1. D01 Chicken Rice $3.50" + "\n" +
                "2. D02 Nasi Lemak $3.00" + "\n" +
                "3. D04 Mee Siam $3.50" + "\n" +
                "4. D04 Mee Siam $3.50", order.toString());
    }

    @Test
    public void testOrderSummary() {
        MenuItem dish01 = new MenuItem("D01", "Chicken Rice", 3.50);
        MenuItem dish02 = new MenuItem("D02", "Nasi Lemak", 3.00);
        MenuItem dish04 = new MenuItem("D04", "Mee Siam", 3.50);
        MenuItem dish05 = new MenuItem("D04", "Mee Siam", 3.50);
        Order order = new Order();
        order.add(dish01);
        order.add(dish02);
        order.add(dish04);
        order.add(dish05);
        assertEquals(order.getID() + " {Total Price: " + order.getTotalPrice() + "}", order.getOrderSummary());
    }
}
