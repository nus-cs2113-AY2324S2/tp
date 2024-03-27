package command;

import java.lang.reflect.Executable;

public class ErrorCommand extends Command {

    @Override
    public void execute() {
        textBox.setNextError("Invalid Command here");
    }
}
