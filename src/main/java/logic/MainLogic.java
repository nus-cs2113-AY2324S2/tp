package logic;

import command.CreateOrderCommand;
import command.ExitCommand;
import command.HelpCommand;
import command.ViewOrderCommand;
import command.ViewOrdersSummaryCommand;
import command.ViewMenuCommand;

import model.Menu;
import model.MenuItem;
import model.Order;
import model.SetMenu;
import ui.CommandType;
import ui.Parser;

import java.util.ArrayList;
import java.util.Scanner;

public class MainLogic {
    public static void main(String[] args) {
        //Initialise all required models
        System.out.println("Hello from DinEz");
        Scanner input = new Scanner(System.in);
        ArrayList<Order> ordersList = new ArrayList<>();
        ArrayList<Menu> menusList = new ArrayList<>();

        initMenu(menusList);

        //for testing
        testOrderAddAndRemove(ordersList);
        testOrderAddAndRemove(ordersList);

        boolean isExit = false;
        while (!isExit) {
            String inputText = input.nextLine();
            CommandType commandType;
            try {
                commandType = Parser.analyzeInput(inputText);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command");
                HelpCommand.execute();
                continue;
            }
            switch (commandType) {
            case EXIT:
                isExit = ExitCommand.execute(isExit);
                break;
            case HELP:
                HelpCommand.execute();
                break;
            case CREATE_ORDER:
                //GOTO sub-menu to add/remove menuItems
                Order newOrder = CreateOrderCommand.execute();
                ordersList.add(newOrder);
                break;
            case VIEW_MENU:
                ViewMenuCommand.execute(menusList);
            case VIEW_ORDER:
                ViewOrderCommand.execute(ordersList, inputText);
                break;
            case VIEW_ALL_ORDERS:
                ViewOrdersSummaryCommand.execute(ordersList);
                break;
            default:
                System.out.println("Invalid command");
                HelpCommand.execute();
            }
        }
    }

    private static void initMenu(ArrayList<Menu> menusList) {
        MenuItem dish01 = new MenuItem("001", "Chicken Rice", 3.50);
        MenuItem dish02 = new MenuItem("002", "Nasi Lemak", 3.00);
        MenuItem dish03 = new MenuItem("0O3", "Hokkien Mee", 4.00);
        MenuItem dish04 = new MenuItem("004", "Mee Siam", 3.50);
        MenuItem dish05 = new MenuItem("005", "Fishball Noodles", 3.00);
        MenuItem dish06 = new MenuItem("0O6", "Chicken Curry Rice", 5.00);
        MenuItem dish07 = new MenuItem("007", "Seafood Fried Rice", 5.50);
        MenuItem dish08 = new MenuItem("008", "Roasted delight set", 6.50);
        MenuItem dish09 = new MenuItem("009", "Hotplate beef set", 7.00);
        MenuItem dish10 = new MenuItem("010", "Kimchi noodles", 4.00);

        Menu menuV1 = new Menu(SetMenu.Dinner);
        menuV1.add(dish01);
        menuV1.add(dish02);
        menuV1.add(dish03);
        menuV1.add(dish04);
        menuV1.add(dish05);
        menuV1.add(dish06);
        menuV1.add(dish07);
        menuV1.add(dish08);
        menuV1.add(dish09);
        menuV1.add(dish10);
        menusList.add(menuV1);

    }

    public static void testOrderAddAndRemove(ArrayList<Order> ordersList) {
        MenuItem dish01 = new MenuItem("D01", "Chicken Rice", 3.50);
        MenuItem dish02 = new MenuItem("D02", "Nasi Lemak", 3.00);
        MenuItem dish04 = new MenuItem("D04", "Mee Siam", 3.50);
        MenuItem dish05 = new MenuItem("D04", "Mee Siam", 3.50);
        Order order = new Order();
        order.add(dish01);
        order.add(dish02);
        order.add(dish04);
        order.add(dish05);
        ordersList.add(order);
    }
}
