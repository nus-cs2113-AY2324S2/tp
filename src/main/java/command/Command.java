package command;

public abstract class Command {
    protected String commandDescription;
    public abstract void execute();
}
