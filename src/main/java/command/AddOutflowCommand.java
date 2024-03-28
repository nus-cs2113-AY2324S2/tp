package command;

import financialtransactions.Outflow;
import financialtransactions.TransactionManager;

public class AddOutflowCommand extends BaseCommand {

    public AddOutflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) {
        String outflowName = null;
        double outflowAmount = 0.0;
        String outflowDate = null;
        String outflowTime = null;
        String outflowCategory = null;

        for (String part : commandParts) {
            if (part.startsWith("n/")) {
                outflowName = part.substring(2);
            } else if (part.startsWith("a/")) {
                outflowAmount = Double.parseDouble(part.substring(2));
            } else if (part.startsWith("d/")) {
                outflowDate = part.substring(2);
            } else if (part.startsWith("t/")) {
                outflowTime = part.substring(2);
            } else if (part.startsWith("c/")) {
                outflowCategory = part.substring(2);
            }
        }
        String outflowDateTime = outflowDate + " " + outflowTime;

        Outflow outflow = new Outflow(outflowName, outflowAmount, outflowDateTime);
        outflow.setCategory(Outflow.Category.valueOf(outflowCategory.toUpperCase()));
        manager.addTransaction(outflow);
        return "Ok. Added outflow";
    }
}
