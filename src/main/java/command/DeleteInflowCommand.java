package command;

import financialtransactions.Outflow;
import financialtransactions.TransactionManager;

public class DeleteInflowCommand extends BaseCommand {
    public DeleteInflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) throws Exception {
        String inflowIndex = null;
        for (String part : commandParts) {
            if (part.startsWith("n/")) {
                inflowIndex = part.substring(2);
            }
        }
        assert inflowIndex != null;
        manager.removeInflow(Integer.parseInt(inflowIndex));
        return "Ok. Inflow deleted";
    }
}
