package command;

import exception.CommandInputException;

public enum CommandType {
    WORK("work"),
    REST("rest"),
    EXIT("bye"),
    EXERCISE("exercise"),
    STATUS("status");


    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType analyseInput(String userInput) throws CommandInputException {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.command.equals(userInput.toLowerCase().trim())) {
                return commandType;
            }
        }
        throw new CommandInputException("Invalid command, please try again!\n");
    }
}
