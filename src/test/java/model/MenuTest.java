package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {
    @Test
    public void testEmptyMenu() {
        Menu menu = new Menu("001");
        assertEquals(
                "+--------------------------------------+\n" +
                        "|              MENU                    |\n" +
                        "+------+-------------------------------+\n" +
                        "| ID   | Name                  | Price |\n" +
                        "+------+-------------------------------+\n" +
                        "+------+-------------------------------+\n"
                , menu.toString());

    }
    /*@Test
    public void testMenuAddAndRemove() {
<<<<<<< HEAD
        MenuItem dish01 = new MenuItem("L01", "Chicken Rice", 3.50);
        MenuItem dish02 = new MenuItem("L02", "Nasi Lemak", 3.00);
        MenuItem dish04 = new MenuItem("L04", "Mee Siam", 3.50);
        Menu menu = new Menu("01");
=======
        MenuItem dish01 = new MenuItem("001", "Chicken Rice", 3.50);
        MenuItem dish02 = new MenuItem("002", "Nasi Lemak", 3.00);
        MenuItem dish04 = new MenuItem("004", "Mee Siam", 3.50);
        Menu menu = new Menu(SetMenu.Lunch);
>>>>>>> 7eaea5207c067badfa502d56d5677a8b74228098
        menu.add(dish01);
        assertEquals(menu.getID() + "\n" +
                "1. 001 Chicken Rice $3.50", menu.toString());
        menu.add(dish02);
        assertEquals(menu.getID() + "\n" +
                "1. 001 Chicken Rice $3.50" + "\n" +
                "2. 002 Nasi Lemak $3.00", menu.toString());
        menu.add(dish04);
        assertEquals(menu.getID() + "\n" +
                "1. 001 Chicken Rice $3.50" + "\n" +
                "2. 002 Nasi Lemak $3.00" + "\n" +
                "3. 004 Mee Siam $3.50" , menu.toString());
        menu.remove(2);
        assertEquals(menu.getID() + "\n" +
                "1. 001 Chicken Rice $3.50" + "\n" +
                "2. 004 Mee Siam $3.50", menu.toString());
        menu.remove("L04");
        assertEquals(menu.getID() + "\n" +
                "1. 001 Chicken Rice $3.50", menu.toString());
    }
*/
}
