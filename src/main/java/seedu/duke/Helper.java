package seedu.duke;

import java.util.ArrayList;

public class Helper {
    private static final ArrayList<Command> commandList = new ArrayList<Command>();

    public Helper() {
        commandList.add(new Command("bye", "Terminate the programme", "bye"));
        commandList.add(new Command("help", "View available commands or check a specific command", "help, help [command]"));
        commandList.add(new Command("solution", "View solution to the question", "solution [QUESTION_INDEX]"));
        commandList.add(new Command("explain", "View explaination for the solution", "explain [QUESTION_INDEX]"));
    }

    public static Object[][] listAllCommands() {
        int commandNum = commandList.size();
        Object[][] tableData = new Object[commandNum][];
        for (int i = 0; i < commandNum; i++) {
            tableData[i] = new Object[]{commandList.get(i).getCommandName(), commandList.get(i).getCommandFunction(), commandList.get(i).getCommandUsage()};
        }
        return tableData;
    }
}
