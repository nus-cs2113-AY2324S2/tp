package seedu.stockpal.commands;

import seedu.stockpal.data.product.Product;


public class NewCommand extends Command {
    public static final String COMMAND_KEYWORD = "new";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";

    private static Integer pid = 1;
    private final Product toAdd;


    public NewCommand(String name,
                      Integer quantity,
                      Double price,
                      String description
                      ) {
        this.toAdd = new Product(name, quantity, price, description, pid++);
    }


    @Override
    public void execute() {
        //super.execute();
        productList.addProduct(toAdd);
        //return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        System.out.println("Added product!");
    }
}
