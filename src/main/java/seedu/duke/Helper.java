package seedu.duke;

import java.util.ArrayList;

public class Helper {
    private static final ArrayList<Command> commandList = new ArrayList<Command>();

    public Helper() {
        commandList.add(new Command("bye", "Terminate the programme", "bye"));
        commandList.add(new Command("help", "View available commands or check a one", "help, help [command]"));
        commandList.add(new Command("solution", "View solution to the question", "solution [QUESTION_INDEX]"));
        commandList.add(new Command("explain", "View explaination for the solution", "explain [QUESTION_INDEX]"));
    }

    public static String[][] listAllCommands() {
        int commandNum = commandList.size();
        String[][] tableData = new String[commandNum][];
        for (int i = 0; i < commandNum; i++) {
            tableData[i] = new String[]{
                commandList.get(i).getCommandName(),
                commandList.get(i).getCommandFunction(),
                commandList.get(i).getCommandUsage()
            };
        }
        return tableData;
    }
}
