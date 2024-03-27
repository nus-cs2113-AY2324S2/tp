package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.frontend.UI;

public class HelpCommand extends Command {
    private static final String HELP_MESSAGE = "Here are the commands you can use:" + System.lineSeparator()
            + "\t1. add /n <description> /d <date> /a <amount> [/c <category>] - adds an expense to the dashboard"
            + System.lineSeparator()
            + "\t2. delete <index> - deletes an expense from the dashboard"
            + System.lineSeparator()
            + "\t3. list - lists all expenses in the dashboard"
            + System.lineSeparator()
            + "\t4. summarise [/from <start>] [/to <end>] - summarises the expenses in the dashboard"
            + System.lineSeparator()
            + "\t5. category <list|add|delete> [<category if add|delete specified>] - manages categories"
            + System.lineSeparator()
            + "\t6. event /n <event name> /d <event description> - adds an event"
            + System.lineSeparator()
            + "\t7. exit - exits the application"
            + System.lineSeparator()
            + "Notes about the command format:" + System.lineSeparator()
            + "\t1. <word> are to be provided by the user without the <>" + System.lineSeparator()
            + "\t2. [/w word] are optional arguments";

    @Override
    public void execute(Dashboard dashboard) {
        UI.prettyPrint(HELP_MESSAGE);
    }
}
