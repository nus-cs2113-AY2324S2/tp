package command;

import financialtransactions.Outflow;
import financialtransactions.TransactionManager;

public class EditOutflowCommand extends BaseCommand {
    public EditOutflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) throws Exception {
        int outflowIndex = -1;
        String outflowName = null;
        double outflowAmount = 0.0;
        String outflowDate = null;
        String outflowTime = null;
        String outflowCategory = null;

        for (String part : commandParts) {
            if (part.startsWith("i/")) {
                outflowIndex = Integer.parseInt(part.substring(2));
            } else if (part.startsWith("r/")) {
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
        Outflow updatedOutflow = new Outflow(outflowName, outflowAmount, outflowDateTime);
        updatedOutflow.setCategory(Outflow.Category.valueOf(outflowCategory.toUpperCase()));
        manager.editOutflow(outflowIndex, updatedOutflow);
        return "Ok. Edited outflow";
    }
}
