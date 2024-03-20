package logic;

import command.MainCreateOrderCommand;
import command.MainExitCommand;
import command.MainHelpCommand;
import command.ViewOrderCommand;
import command.ViewOrdersSummaryCommand;
import model.Menu;
import model.MenuItem;
import model.Order;
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

        //for testin
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
                MainHelpCommand.execute();
                continue;
            }
            switch (commandType) {
            case EXIT:
                isExit = MainExitCommand.execute(isExit);
                break;
            case HELP:
                MainHelpCommand.execute();
                break;
            case CREATE_ORDER:
                //GOTO sub-menu to add/remove menuItems
                Order newOrder = MainCreateOrderCommand.execute(inputText, menusList); //inputText is passed to detect menu selected
                ordersList.add(newOrder);
                break;
            case VIEW_ORDER:
                ViewOrderCommand.execute(ordersList, inputText);
                break;
            case VIEW_ALL_ORDERS:
                ViewOrdersSummaryCommand.execute(ordersList);
                break;
            default:
                System.out.println("Invalid command");
                MainHelpCommand.execute();
            }
        }
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
