package commands;

import exceptions.Wellness360Exception;

public class ExitCommand implements Command {


    public ExitCommand(String commandArgs) throws Wellness360Exception {
        if (!commandArgs.isEmpty()) {
            throw new Wellness360Exception("Unknown command");
        }
    }

    @Override
    public void execute() {
        return;
    }


    @Override
    public boolean isExit() {
        return true;
    }
}
