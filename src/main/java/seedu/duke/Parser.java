package seedu.duke;

import seedu.duke.exceptions.CustomException;

public class Parser {
    private static final int PARAMETER_INDEX = 1;

    public void parseCommand(String command, Ui ui, QuestionsList questionsList, Helper helper) throws CustomException {
        String lowerCaseCommand = command.toLowerCase();

        if (lowerCaseCommand.startsWith("bye")) {
            ui.isPlaying = false;
        } else if (lowerCaseCommand.startsWith("solution") || lowerCaseCommand.startsWith("explain")) {
            processSolutionCommand(lowerCaseCommand, ui, questionsList);
        } else if (lowerCaseCommand.startsWith("help")) {
            processHelpCommand(lowerCaseCommand, ui, helper);
        } else {
            throw new CustomException("-1 HP coz invalid command");
        }
    }

    // user enters "solution 1" to get solution for question1 OR
    // user enters "solution -all" to get ALL solutions
    // also works for "explain 1"
    private void processSolutionCommand(String lowerCaseCommand, Ui ui, QuestionsList questionsList)
            throws CustomException {
        boolean isSolutionCommand = lowerCaseCommand.startsWith("solution");
        String typeOfCommand = isSolutionCommand ? "solution" : "explain";

        String[] commandParts = lowerCaseCommand.split(" ");
        if (commandParts.length != 2) {
            throw new CustomException("invalid " + typeOfCommand + " command");
        }
        // check validity of parameter
        String commandParameter = commandParts[PARAMETER_INDEX];
        try {
            // if parameter is an Integer
            int questionNum = Integer.parseInt(commandParameter);
            // checks validity of parameter
            if (questionNum < 1 || questionNum > questionsList.getSize() + 1) {
                throw new CustomException("booo no such question");
            }
            if (isSolutionCommand) {
                String solution = questionsList.getOneSolution(questionNum);
                ui.printOneSolution(questionNum, solution);
                return;
            } // only runs if explanation
            String explanation = questionsList.getOneExplanation(questionNum);
            ui.printOneSolution(questionNum, explanation);

        } catch (NumberFormatException e) {
            // if parameter is a String
            if (!commandParameter.contentEquals("-all")) {
                throw new CustomException("invalid " + typeOfCommand + " parameter");
            }
            if (!isSolutionCommand) {
                throw new CustomException("There is no \"explain -all\" command");
            }

            String allSolutions = questionsList.getAllSolutions();
            ui.printAllSolutions(allSolutions);
        }
    }

    private void processHelpCommand(String lowerCaseCommand, Ui ui, Helper helper) throws CustomException {
        String[] commandParts = lowerCaseCommand.split(" ");
        if (commandParts.length != 1 && commandParts.length != 2) {
            throw new CustomException("invalid help command parameter");
        }

        if (commandParts.length == 1) {
            Object[][] printData = helper.listAllCommands();
            String[] tableHeader = {"command", "function", "usage"};
            ui.printTable(tableHeader, printData);
        } else {
            // TODO: given a command, find and print the detailed usage for that command
        }
    }
}

