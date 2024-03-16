package seedu.binbash.command;

import seedu.binbash.ItemList;

import java.util.regex.Pattern;

public class SearchCommand extends Command {

    public static final String COMMAND_STRING = "search";
    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("search\\s+(?<keyword>.+?)\\s*$");
    protected String keyword;

    public SearchCommand(ItemList itemList, String keyword) {
        super(itemList);

        this.keyword = keyword;
    }

    public String execute() {
        return itemList.searchItem(keyword);
    }
}
