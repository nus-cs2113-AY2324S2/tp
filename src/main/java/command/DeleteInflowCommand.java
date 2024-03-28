package command;

import financialtransactions.TransactionManager;

public class DeleteInflowCommand extends BaseCommand {
    public DeleteInflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) throws Exception {
        String inflowIndex = null;
        for (String part : commandParts) {
            if (part.startsWith("i/")) {
                inflowIndex = part.substring(2);
            }
        }
        assert inflowIndex != null : "inflowIndex should not be null";
        manager.removeInflow(Integer.parseInt(inflowIndex));
        return "Ok. Inflow deleted";
    }
}
