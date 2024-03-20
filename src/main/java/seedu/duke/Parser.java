package seedu.duke;

import seedu.duke.exceptions.CustomException;

public class Parser {
    private static final int PARAMETER_INDEX = 1;
  
    private static final int NO_PARAMETERS = 1;
    private static final int ONE_PARAMETER = 2;
    private static final int TWO_PARAMETERS = 3;
    private static final int FIRST_PARAMETER = 1;
    private static final int SECOND_PARAMETER = 2;

    private static final String QUESTION_PARAMETER = "questions";
    private static final String COMMAND_SPLITTER = " ";

    private static final boolean INCLUDES_QUESTION = true;
    boolean hasChosenTopic = false;

    public void parseCommand(
            String command, Ui ui, QuestionsList questionsList,
            TopicList topicList, QuestionListByTopic questionListByTopic, ResultsList allResults
    ) throws CustomException {
        String lowerCaseCommand = command.toLowerCase();
        if (ui.isPlaying) {

            if (lowerCaseCommand.startsWith("topic") && !hasChosenTopic) {
                processStartCommand(lowerCaseCommand, ui, topicList, questionListByTopic, allResults);
                //hasChosenTopic = true;
            } else if (lowerCaseCommand.startsWith("topic") && hasChosenTopic) {
                throw new CustomException("Please choose a topic in the format: topic [INDEX]");
            }

            if (lowerCaseCommand.startsWith("bye")) {
                ui.isPlaying = false;
            } else if (lowerCaseCommand.startsWith("solution") || lowerCaseCommand.startsWith("explain")) {
                //System.out.println("ERROR");
                processSolutionCommand(lowerCaseCommand, ui, questionsList, topicList, questionListByTopic);
            } else if (lowerCaseCommand.startsWith("results")) {
                processResultsCommand(lowerCaseCommand, allResults, ui, questionListByTopic);
            } else if (lowerCaseCommand.startsWith("help")) {
                processHelpCommand(lowerCaseCommand, ui, helper);
            } else {
                throw new CustomException("-1 HP coz invalid command");
            }
        }

    }

    private void processResultsCommand(String lowerCaseCommand, ResultsList allResults, Ui ui,
                                       QuestionListByTopic questionListByTopic) {
        String[] commandParts = lowerCaseCommand.split(COMMAND_SPLITTER, TWO_PARAMETERS);
        switch (commandParts.length) {
        case (NO_PARAMETERS): {
            ui.printAllResults(!INCLUDES_QUESTION, allResults, questionListByTopic);
            break;
        }
        case (ONE_PARAMETER): {
            if (commandParts[PARAMETER_INDEX].equals(QUESTION_PARAMETER)) {
                ui.printAllResults(INCLUDES_QUESTION, allResults, questionListByTopic);
            } else {
                int index = Integer.parseInt(commandParts[FIRST_PARAMETER]);
                String score = allResults.getSpecifiedResult(index - 1).getScore();
                int topicNum = allResults.getTopicNum(index - 1);
                ui.printOneResult(!INCLUDES_QUESTION, topicNum, score, questionListByTopic);
            }
            break;
        }
        case (TWO_PARAMETERS): {
            int index = Integer.parseInt(commandParts[SECOND_PARAMETER]);
            String score = allResults.getSpecifiedResult(index - 1).getScore();
            int topicNum = allResults.getTopicNum(index - 1);
            ui.printOneResult(INCLUDES_QUESTION, topicNum, score, questionListByTopic);
            break;
        }
        default: {
            break;
        }
        }
    }

    private void processStartCommand (
            String lowerCaseCommand, Ui ui, TopicList topicList, QuestionListByTopic questionListByTopic,
            ResultsList allResults
    ) throws CustomException {

        String[] commandParts = lowerCaseCommand.split(" ");
        if (commandParts.length != 2) {
            throw new CustomException("invalid " + lowerCaseCommand + " command");
        }
        String commandParameter = commandParts[PARAMETER_INDEX];
        try {
            // if parameter is an Integer
            int topicNum = Integer.parseInt(commandParameter);
            // checks validity of parameter
            if (topicNum < 1 || topicNum > topicList.getSize() + 1) {
                throw new CustomException("booo no such topic");
            }
            ui.printChosenTopic(topicNum, topicList, questionListByTopic, allResults);

        } catch (NumberFormatException e) {
            throw new CustomException("invalid " + lowerCaseCommand + " parameter");
        }

    }

    // user enters "solution 1" to get solution for question1 OR
    // user enters "solution -all" to get ALL solutions
    // also works for "explain 1"
    private void processSolutionCommand(
            String lowerCaseCommand, Ui ui, QuestionsList questionsList,
            TopicList topicList, QuestionListByTopic questionListByTopic
    ) throws CustomException {
        boolean isSolutionCommand = lowerCaseCommand.startsWith("solution");
        String typeOfCommand = isSolutionCommand ? "solution" : "explain";

        String[] commandParts = lowerCaseCommand.split(" ");
        if (commandParts.length != 3) {
            throw new CustomException("invalid " + typeOfCommand + " command. Format: solution TOPIC QUESTION_INDEX");
        }

        // check validity of parameter
        String commandParameterTopic = commandParts[PARAMETER_INDEX];
        String commandParameterQn = commandParts[PARAMETER_INDEX + 1];

        try {
            // if parameter is an Integer
            int topicNum = Integer.parseInt(commandParameterTopic);
            int questionNum = Integer.parseInt(commandParameterQn);

            // checks validity of parameter
            if ((topicNum < 1 || topicNum > topicList.getSize())) {
                throw new CustomException("booo no such topic");
            }

            QuestionsList qnList = questionListByTopic.getQuestionSet(topicNum - 1);
            if (questionNum < 1 || questionNum > qnList.getSize()) {
                throw new CustomException(("booo no such question"));
            }

            if (isSolutionCommand) {
                String solution = qnList.getOneSolution(questionNum);
                ui.printOneSolution(questionNum, solution);
                return;
            } // only runs if explanation

            String explanation = qnList.getOneExplanation(questionNum);
            ui.printOneSolution(questionNum, explanation);

        } catch (NumberFormatException e) {
            // if parameter is a String
            if (!commandParameterQn.contentEquals("-all")) {
                throw new CustomException("invalid " + typeOfCommand + " parameter");
            }
            if (!isSolutionCommand) {
                throw new CustomException("There is no \"explain -all\" command");
            }
            int topicNum = Integer.parseInt(commandParameterTopic);
            QuestionsList qnList = questionListByTopic.getQuestionSet(topicNum - 1);
            String allSolutions = qnList.getAllSolutions();
            ui.printAllSolutions(allSolutions);
        }

    }

    public void handleAnswerInputs(String[] inputAnswers, int index, String answer, Question questionUnit,
                                   Results topicResults){
        inputAnswers[index] = answer;
        String correctAnswer = questionUnit.getSolution();
        if (answer.equals(correctAnswer)){
            topicResults.increaseCorrectAnswers();
        }
    }

    private void processHelpCommand(String lowerCaseCommand, Ui ui, Helper helper) throws CustomException {
        String[] commandParts = lowerCaseCommand.split(" ");
        if (commandParts.length != 1 && commandParts.length != 2) {
            throw new CustomException("invalid help command parameter");
        }

        if (commandParts.length == 1) {
            String[][] printData = helper.listAllCommands();
            String[] tableHeader = {"command", "function", "usage"};
            ui.printTable(tableHeader, printData);
        } else {
            // TODO: given a command, find and print the detailed usage for that command
        }
    }
}

