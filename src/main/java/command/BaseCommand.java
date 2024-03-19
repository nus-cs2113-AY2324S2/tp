package command;

import financialtransactions.TransactionManager;

public abstract class BaseCommand {
    public boolean isExit;
    String[] commandParts;
    
    public BaseCommand(Boolean isExit, String[] commandParts){
        this.isExit = isExit;
        this.commandParts = commandParts;
    }
    public abstract String execute(TransactionManager manager) throws Exception;

    public boolean isExit() {
        return this.isExit;
    }
}
