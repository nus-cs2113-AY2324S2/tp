package command;

import financialtransactions.TransactionManager;

public class DeleteOutflowCommand extends BaseCommand {

    public DeleteOutflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) throws Exception{
        if(manager.removeTransaction(Integer.parseInt(this.commandParts[1]))){
            return "Transaction has been removed";
        } else{
            throw new Exception("Error while removing outflow");
        }
        
    }
}
