package parser;


public class Parser {

    //TODO: to add individual classes for each feature in the determineCommand method input parameters
    public static void determineCommand(String userInput) {
        String[] userWords = userInput.trim().split("\\s+", 2);
        String userCommandSection = userWords[0];

        String commandArgs = userWords.length == 2 ? userWords[1] : "";

        switch (userCommandSection) {
        //TODO: to add individual return commands for each feature
        case "reflect":
        case "habit":
        case "sleep":
        case "fitness":
        case "focus":
        case "exit":
        default:
        //TODO: to add throw exception for unknown command
        }
    }
}
