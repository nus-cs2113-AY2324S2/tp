package model;

import java.util.ArrayList;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;


import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static model.SetMenu.Breakfast;
import static model.SetMenu.Lunch;
import static model.SetMenu.Dinner;

public class Menu implements ItemManager {
    private final ArrayList<MenuItem> menuItemList = new ArrayList<>();

    private final String menuID;

    private final static Logger logr = Logger.getLogger("MenuLogger");

    public Menu(SetMenu menuType) {
        Menu.setupLogger();
        switch (menuType) {
        case Breakfast:
            this.menuID = String.valueOf(Breakfast);
            break;
        case Lunch:
            this.menuID = String.valueOf(Lunch);
            break;
        case Dinner:
            this.menuID = String.valueOf(Dinner);
            break;
        default:
            this.menuID = "No Menu type";
        }

    }

    @Override
    public String getID() {
        return menuID;
    }
    @Override
    public void add(MenuItem item) {
        this.menuItemList.add(item);

    }

    /**
     * Removes an item from the menu by its corresponding number
     * @param menuItemNum The number of the item in the menu
     */
    @Override
    public void remove(int menuItemNum) {
        try {
            this.menuItemList.remove(menuItemNum - 1);

        } catch (IndexOutOfBoundsException e) {
            logr.log(Level.SEVERE, "You tried removing an item belonging to an index outside the valid range of the ArrayList",e);
        }
    }

    /**
     * Removes an item from the menu by its name
     * @param name The name of the item
     */
    @Override
    public void remove(String name) {
        this.menuItemList.removeIf(x -> x.getID().equals(name));
    }

    @Override
    public String toString() {
        return this.menuID + "\n" +
                IntStream.range(0,this.menuItemList.size())
                        .mapToObj(x -> (x + 1) + ". " + this.menuItemList.get(x))
                        .collect(Collectors.joining("\n"));
    }

    /**
     * Set up logger for this class. It has two handlers, one FileHandler and one ConsoleHandler
     * FileHandler records log messages from FINE and above
     * ConsoleHandler only records SEVERE messages
     */
    private static void setupLogger() {
        LogManager.getLogManager().reset();
        logr.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logr.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("MenuLogger.log");
            fh.setLevel(Level.FINE);
            logr.addHandler(fh);
        } catch (java.io.IOException e) {
            logr.log(Level.SEVERE, "File logger not working.",e);
        }
    }
}
