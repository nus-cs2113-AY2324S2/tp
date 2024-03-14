package byteceps.commands;

import java.util.ArrayList;

public class Parser {
    private String command;
    private InputArguments commandAction;
    private ArrayList<InputArguments> additionalArguments;

    public Parser() {
        flush();
    }

    private void flush() {
        command = "";
        commandAction = null;
        additionalArguments = new ArrayList<>();
    }

    public void parseInput(String line) {
        // flush the old input
        flush();

        int indexOfFirstSlash = line.indexOf('/');

        // input does not have parameters
        if (indexOfFirstSlash == -1) {
            command = line.trim().toLowerCase();
            return;
        }

        command = line.substring(0, indexOfFirstSlash).trim().toLowerCase();
        String[] argumentKeyValuePairs = line.substring(indexOfFirstSlash + 1).split("/");
        for (String keyValuePair : argumentKeyValuePairs) {
            String[] currentKV = keyValuePair.split( " ", 2);
            String flag = currentKV[0];
            String parameter = currentKV[1];

            InputArguments currentInputArgument = new InputArguments(flag, parameter);

            if (commandAction == null) {
                commandAction = currentInputArgument;
            } else {
                additionalArguments.add(currentInputArgument);
            }
        }

        System.out.printf("parsed input: %s, %s, %s\n", command, commandAction.getFlag(), commandAction.getParameter());
    }

    public Command parseCommand() {
        System.out.println("parsing command... " + command);
        switch(command) {
        case ExerciseCommand.COMMAND_WORD:
            return new ExerciseCommand(commandAction, additionalArguments);
        case WorkoutCommand.COMMAND_WORD:
            return new WorkoutCommand(commandAction, additionalArguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        }

        return null;
    }

    public String getCommandString() {
        return command;
    }

    public String getAction() {
        return commandAction.getFlag();
    }

    public String getActionParameter() {
        return commandAction.getParameter();
    }

    public ArrayList<InputArguments> getAdditionalArguments() {
        return additionalArguments;
    }

    @Override
    public String toString() {
        return "COMMAND: " + System.lineSeparator() + command + System.lineSeparator() +
                "ARGUMENTS: " + System.lineSeparator() + additionalArguments.toString();
    }
}
