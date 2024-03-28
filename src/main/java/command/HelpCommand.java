package command;

import common.Messages;

public class HelpCommand extends Command{

    @Override
    public void execute() {
        System.out.println(Messages.HELP);
    }
}
