package seedu.stockpal.commands;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.ui.Ui;

//@@author EdmundTangg
public class FindCommand extends ListActionCommand{
        public static final String COMMAND_KEYWORD = "find";
        public static final String COMMAND_USAGE = Ui.indentTextIfRequired(COMMAND_KEYWORD
            + ": Lists all products containing KEYWORD."
            + Messages.LINE_SEPARATOR
            + "Format: find KEYWORD"
        );

        private final String keyword;

        public FindCommand(String keyword) {
            this.keyword = keyword.toLowerCase();
        }

        @Override
        public void execute(ProductList productList) throws StockPalException {
            ProductList.findKeyword(productList, keyword);
        }
}
