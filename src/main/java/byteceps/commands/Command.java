package byteceps.commands;

import java.util.ArrayList;

public abstract class Command {
    private String action;
    private String actionParameters;
    private ArrayList<InputArguments> additionalArguments;

    public Command(InputArguments commandAction, ArrayList<InputArguments> additionalArguments) {
        this.action = commandAction.getFlag();
        this.actionParameters = commandAction.getParameter();
        this.additionalArguments = additionalArguments;
    }

    public Command() {
    }

    public abstract CommandResult execute();

    public String getAction() {
        return action;
    }

    public String getActionParameters() {
        return actionParameters;
    }

    public ArrayList<InputArguments> getAdditionalArguments() {
        return additionalArguments;
    }

}
