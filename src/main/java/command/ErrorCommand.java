package command;

import java.lang.reflect.Executable;

public class ErrorCommand extends Command{
    Exception exception;

    public ErrorCommand(Exception exception) {
        this.exception = exception;
    }

    @Override
    public void execute() {
        System.out.println(exception.getMessage());
    }
}
