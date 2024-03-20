package command;

import logic.OrderLogic;
import model.Menu;
import model.Order;

import java.util.ArrayList;

public class MainCreateOrderCommand implements MainCommand{

    public static Order execute(String inputText, ArrayList<Menu> menusList) {
        //TODO: get menuID from inputText and pass the menu into createNewOrder
        return OrderLogic.createNewOrder();
    }
}
