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
        baseString += "1) add-inflow n/ a/ d/ t/ c/\n";
        baseString += "2) add-outflow n/ a/ d/ t/ c/\n";
        baseString += "3) delete-inflow i/\n";
        baseString += "4) delete-outflow i/\n";
        baseString += "5) edit-inflow i/ n/ a/ d/ t/ c/\n";
        baseString += "6) edit-outflow i/ n/ a/ d/ t/ c/\n";
        baseString += "7) view-history INDEX \n";
        baseString += "8) quit \n";
        baseString += "_____________";
        return baseString;
    }
}
