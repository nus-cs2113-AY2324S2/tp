package commands;

public interface Command {

    //to add throw exception for execute method
    void execute();
    boolean isExit();
}
