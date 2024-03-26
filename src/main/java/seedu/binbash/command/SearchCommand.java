package seedu.binbash.command;

import seedu.binbash.ItemList;
import seedu.binbash.ui.Ui;
import seedu.binbash.storage.Storage;

import java.util.regex.Pattern;

public class SearchCommand extends Command {

    public static final Pattern COMMAND_FORMAT = Pattern.compile("search\\s+(?<keyword>.+?)\\s*$");
    private final String keyword;

    public SearchCommand(String keyword) {
        this.keyword = keyword;
        commandLogger.fine(String.format(
                "Creating Search Command... Keyword: %s",
                keyword
        ));
    }

    public boolean execute(Ui ui, ItemList itemList, Storage storage) {
        executionUiOutput = itemList.searchItem(keyword);
        return true;
    }
}
