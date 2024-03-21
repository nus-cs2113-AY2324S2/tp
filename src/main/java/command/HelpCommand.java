package command;

import financialtransactions.TransactionManager;

public class HelpCommand extends BaseCommand {
    public HelpCommand(String[] commandParts) {
        super(false, commandParts);
    }

    @Override
    public String execute(TransactionManager manager) throws Exception {
        String baseString = "";
        baseString += "Here are the available commands: \n";
        baseString += "1) add-inflow n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY\n";
        baseString += "2) add-outflow n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY\n";
        baseString += "3) delete-inflow i/INDEX\n";
        baseString += "4) delete-outflow i/INDEX\n";
        baseString += "5) edit-inflow i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY\n";
        baseString += "6) edit-outflow i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY\n";
        baseString += "7) view-history n/NUM \n";
        baseString += "8) quit \n";
        baseString += "_____________";
        return baseString;
    }
}
