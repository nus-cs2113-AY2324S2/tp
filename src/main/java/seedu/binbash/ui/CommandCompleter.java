package seedu.binbash.ui;

import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.reader.impl.completer.NullCompleter;

public class CommandCompleter extends ArgumentCompleter {
    private static final StringsCompleter COMMAND_COMPLETER = new StringsCompleter(
            "add", "delete", "list", "search", "bye");

    public CommandCompleter() {
        super(COMMAND_COMPLETER, NullCompleter.INSTANCE);
    }
}
