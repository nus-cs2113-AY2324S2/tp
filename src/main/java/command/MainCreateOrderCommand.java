package command;

import logic.OrderLogic;
import model.Menu;
import model.Order;
import ui.Parser;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class MainCreateOrderCommand implements MainCommand{

    public static Optional<Order> execute(Scanner input, String inputText, ArrayList<Menu> menusList) {
        Menu menu;
        try {
            String[] indexString = Parser.splitInput(Parser.analyzeInput(inputText), inputText);
            String menuID = indexString[0];
            menu = menusList.stream().findAny().filter(x -> x.getID()
                                                             .equals(menuID))
                                                             .orElseThrow(IndexOutOfBoundsException::new);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Menu does not exist");
            return Optional.empty();
        }
        return OrderLogic.createNewOrder(input, menu);
    }
}
