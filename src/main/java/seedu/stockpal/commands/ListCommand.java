package seedu.stockpal.commands;

import seedu.stockpal.ui.Ui;

public class ListCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "list";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";

    /**
     * Prints out all products that are in the list.
     * If the list is empty, it tells the user that the list is empty.
     */
    @Override
    public void execute() {
        if (productList.isEmpty()) {
            System.out.println("ProductList is empty");
            return;
        }

        Ui.printListTasks(productList);
    }

}
