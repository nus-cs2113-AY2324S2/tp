package seedu.duke;

import java.util.ArrayList;
import java.util.Random;

public class Helper {
    private static final ArrayList<Command> commandList = new ArrayList<Command>();

    public Helper() {
        commandList.add(
            new Command(
                "topic", "Start the round of questions for the chosen " +
                    "topic","topic [TOPIC_INDEX]"
            )
        );
        commandList.add(
            new Command(
                "help", "View available commands or check one", "help, help [COMMAND]"
            )
        );
        commandList.add(
            new Command(
                "solution", "View solution to the question", "solution [TOPIC_INDEX] [QUESTION_INDEX]"
            )
        );
        commandList.add(
            new Command(
                "explain", "View explanation for the solution", "explain [TOPIC_INDEX] [QUESTION_INDEX]"
            )
        );
        commandList.add(
            new Command(
                "results", "View results for each topic played", "results [RESULTS_INDEX]"
            )
        );
        commandList.add(
            new Command(
                "bye", "Terminate the program", "bye"
            )
        );
        commandList.add(
            new Command(
                "list", "list available topics and their summaries", "list"
            )
        );
    }

    public String[][] listAllCommands() {
        assert getCommandsCount() > 0 : "Available commands list is empty";
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

    public int getCommandsCount() {
        return commandList.size();
    }

    // returns random topic number from 1 to upperLimit - 1
    public int generateRandomNumber(int upperLimit) {
        assert (upperLimit != 1) : "upperLimit == 1 means topicList.getSize() = 0";
        Random random = new Random();
        int randomNumber = 0;
        boolean isCheckingValidTopicNum = true;

        while (isCheckingValidTopicNum) { //random.nextInt() may return 0, which is NOT a valid topicNum
            randomNumber = random.nextInt(upperLimit);
            if (randomNumber != 0) {
                isCheckingValidTopicNum = false;
            }
        }
        return randomNumber;
    }
}

