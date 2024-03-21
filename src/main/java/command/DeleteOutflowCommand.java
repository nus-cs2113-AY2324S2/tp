package command;

import financialtransactions.Outflow;
import financialtransactions.TransactionManager;

public class DeleteOutflowCommand extends BaseCommand {
    public DeleteOutflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) throws Exception {
        String outflowIndex = null;
        for (String part : commandParts) {
            if (part.startsWith("n/")) {
                outflowIndex = part.substring(2);
            }
        }
        assert outflowIndex != null;
        manager.removeOutflow(Integer.parseInt(outflowIndex));
        return "Ok. Outflow deleted";
    }
}
