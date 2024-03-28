package command;

import model.Menu;

public class OrderViewMenuCommand implements MainCommand {

    public static void execute(Menu menu) {
        System.out.println(menu);
    }
}
