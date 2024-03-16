package commands;

import exceptions.Wellness360Exception;

public interface Command {

    //to add throw exception for execute method
    void execute() throws Wellness360Exception;
    boolean isExit();
}
