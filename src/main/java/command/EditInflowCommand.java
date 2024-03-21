package command;

import financialtransactions.Inflow;
import financialtransactions.TransactionManager;

public class EditInflowCommand extends BaseCommand {
    public EditInflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) throws Exception {
        int inflowIndex = -1;
        String inflowName = null;
        double inflowAmount = 0;
        String inflowDate = null;
        String inflowTime = null;
        String inflowCategory = null;

        for (String part : commandParts) {
            if (part.startsWith("i/")) {
                inflowIndex = Integer.parseInt(part.substring(2));
            } else if (part.startsWith("n/")) {
                inflowName = part.substring(2);
            } else if (part.startsWith("a/")) {
                inflowAmount = Double.parseDouble(part.substring(2));
            } else if (part.startsWith("d/")) {
                inflowDate = part.substring(2);
            } else if (part.startsWith("t/")) {
                inflowTime = part.substring(2);
            } else if (part.startsWith("c/")) {
                inflowCategory = part.substring(2);
            }
        }

        String inflowDateTime = inflowDate + " " + inflowTime;
        Inflow updatedInflow = new Inflow(inflowName, inflowAmount, inflowDateTime);
        updatedInflow.setCategory(Inflow.Category.valueOf(inflowCategory.toUpperCase()));
        manager.editInflow(inflowIndex, updatedInflow);
        return "Ok. Edited inflow";
    }
}
