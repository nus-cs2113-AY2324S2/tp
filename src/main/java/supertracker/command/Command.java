package supertracker.command;

public interface Command {
    void execute();
    boolean isQuit();
}
