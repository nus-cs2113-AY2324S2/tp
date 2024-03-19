package seedu.binbash.command;

import seedu.binbash.ItemList;

import java.util.regex.Pattern;

public class SearchCommand extends Command {

    public static final Pattern COMMAND_FORMAT = Pattern.compile("search\\s+(?<keyword>.+?)\\s*$");
    private final String keyword;

    public SearchCommand(ItemList itemList, String keyword) {
        super(itemList);
        this.keyword = keyword;
        commandLogger.fine(String.format(
                "Creating Search Command... Keyword: %s",
                keyword
        ));
    }

    public boolean execute() {
        executionUiOutput = itemList.searchItem(keyword);
        return true;
    }
}
