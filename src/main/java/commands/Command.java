package commands;

import exceptions.Wellness360Exception;

public interface Command {

    void execute() throws Wellness360Exception;
    boolean isExit();
}
