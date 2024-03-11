package seedu.binbash.command;

import java.util.regex.Pattern;
import seedu.binbash.ItemList;

public abstract class Command {
    protected ItemList itemList;
    public static final String COMMAND_STRING = "command";
    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("(?<command>\\S+)(?<arguments>.*)");
    protected Command(ItemList itemList) {
        this.itemList = itemList;
    }

    abstract public String execute();
}
