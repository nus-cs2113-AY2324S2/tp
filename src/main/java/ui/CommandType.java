package ui;

import exception.CommandInputException;

public enum CommandType {
    WORK("(?i)work\\s*"),
    REST("(?i)rest\\s*"),
    EXIT("(?i)bye\\s*"),
    EXERCISE("(?i)exercise\\s*"),
    STATUS("(?i)status\\s*"),
    HELP("(?i)help\\s*");


    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType analyseInput(String userInput) throws CommandInputException {
        for (CommandType commandType : CommandType.values()) {
            if (userInput.matches(commandType.command)) {
                return commandType;
            }
        }
        throw new CommandInputException("Invalid command, please try again!\n");
    }
}
