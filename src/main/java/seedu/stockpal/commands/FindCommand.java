package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.exceptions.StockPalException;

//@@author EdmundTangg
public class FindCommand extends ListActionCommand{
    public static final String COMMAND_KEYWORD = "find";
    public static final String COMMAND_DESCRIPTION = "Lists all products containing KEYWORD.";
    public static final String COMMAND_USAGE = "find KEYWORD";
    public static final String[] COMMAND_FLAGS = {
        "KEYWORD"
    };

    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {
        "Keyword to search for in product name. Keyword is case sensitive."
    };

    private final String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }
    @Override
    public void execute(ProductList productList) throws StockPalException {
        ProductList.findKeyword(productList, keyword);
    }
}
