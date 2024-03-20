package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {
    @Test
    public void testMenuAddAndRemove() {
        MenuItem dish01 = new MenuItem("L01", "Chicken Rice", 3.50);
        MenuItem dish02 = new MenuItem("L02", "Nasi Lemak", 3.00);
        MenuItem dish04 = new MenuItem("L04", "Mee Siam", 3.50);
        Menu menu = new Menu("01");
        menu.add(dish01);
        assertEquals(menu.getID() + "\n" +
                "1. L01 Chicken Rice $3.50", menu.toString());
        menu.add(dish02);
        assertEquals(menu.getID() + "\n" +
                "1. L01 Chicken Rice $3.50" + "\n" +
                "2. L02 Nasi Lemak $3.00", menu.toString());
        menu.add(dish04);
        assertEquals(menu.getID() + "\n" +
                "1. L01 Chicken Rice $3.50" + "\n" +
                "2. L02 Nasi Lemak $3.00" + "\n" +
                "3. L04 Mee Siam $3.50" , menu.toString());
        menu.remove(2);
        assertEquals(menu.getID() + "\n" +
                "1. L01 Chicken Rice $3.50" + "\n" +
                "2. L04 Mee Siam $3.50", menu.toString());
        menu.remove("L04");
        assertEquals(menu.getID() + "\n" +
                "1. L01 Chicken Rice $3.50", menu.toString());
    }
}
