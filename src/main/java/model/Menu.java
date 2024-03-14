package model;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static model.SetMenu.Breakfast;
import static model.SetMenu.Lunch;
import static model.SetMenu.Dinner;

public class Menu implements ItemManager {
    private static int numOfItems = 0;
    private final ArrayList<MenuItem> menuItemList = new ArrayList<>();

    private final String menuItemID;

    public Menu(SetMenu menuType) {
        switch (menuType) {
        case Breakfast:
            this.menuItemID = String.valueOf(Breakfast);
            break;
        case Lunch:
            this.menuItemID = String.valueOf(Lunch);
            break;
        case Dinner:
            this.menuItemID = String.valueOf(Dinner);
            break;
        default:
            this.menuItemID = "No Menu type";
        }

    }

    @Override
    public String getID() {
        return menuItemID;
    }
    @Override
    public void add(MenuItem item) {
        this.menuItemList.add(item);
        numOfItems++;
    }

    /**
     * Removes an item from the menu by its corresponding number
     * @param menuItemNum The number of the item in the menu
     */
    @Override
    public void remove(int menuItemNum) {
        try {
            this.menuItemList.remove(menuItemNum - 1);
            numOfItems--;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid menu item number");
        }
    }

    /**
     * Removes an item from the menu by its name
     * @param name The name of the item
     */
    @Override
    public void remove(String name) {
        this.menuItemList.removeIf(x -> x.getID().equals(name));
        numOfItems--;
    }

    @Override
    public String toString() {
        return this.menuItemID + "\n" +
                IntStream.range(0,this.menuItemList.size())
                        .mapToObj(x -> (x + 1) + ". " + this.menuItemList.get(x))
                        .collect(Collectors.joining("\n"));
    }
}
