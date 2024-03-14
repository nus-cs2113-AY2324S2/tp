package byteceps.commands;

public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "exit";
    public ExitCommand() {
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("exit");
    }
}
