package seedu.duke;

import model.MenuItem;
import model.Order;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        */
        //Scanner in = new Scanner(System.in);
        //System.out.println("Hello " + in.nextLine());

        testModel();
    }

    public static void testModel() {
        MenuItem dish01 = new MenuItem("D01", "Chicken Rice", 3.50);
        MenuItem dish02 = new MenuItem("D02", "Nasi Lemak", 3.00);
        MenuItem dish03 = new MenuItem("D03", "Mee Goreng", 4.00);
        MenuItem dish04 = new MenuItem("D04", "Mee Siam", 3.50);
        MenuItem dish05 = new MenuItem("D04", "Mee Siam", 3.50);
        Order order = new Order();
        order.add(dish01);
        order.add(dish02);
        order.add(dish03);
        order.add(dish04);
        order.add(dish05);
        System.out.println(order);
        System.out.println("Total price: " + order.getTotalPrice());
        order.remove(0);
        order.remove("D04");
        System.out.println(order);
        System.out.println("Total price: " + order.getTotalPrice());
    }

}
