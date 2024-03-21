package seedu.duke;

import seedu.duke.exceptions.CustomException;

import java.util.ArrayList;

public class Parser {
    private static final int PARAMETER_INDEX = 1;
  
    private static final int NO_PARAMETERS = 1;
    private static final int ONE_PARAMETER = 2;
    private static final int TWO_PARAMETERS = 3;
    private static final int FIRST_PARAMETER = 1;
    private static final int SECOND_PARAMETER = 2;

    private static final String DETAILS_PARAMETER = "details";
    private static final String COMMAND_SPLITTER = " ";

    private static final boolean INCLUDES_DETAILS = true;
    private static final boolean IS_CORRECT_ANSWER = true;
    private static final String MESSAGE_NO_RESULTS = "There are no results.";
    private static final String MESSAGE_ERROR = "An error has occurred.";
    private static final String MESSAGE_INVALID_PARAMETERS = "Invalid parameters.";
    private static final int NO_RESULTS = 0;
    private static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "Index is out of bounds.";
    private static final String MESSAGE_INVALID_INDEX = "Index must be an integer.";
    boolean hasChosenTopic = false;

    public void parseCommand(
            String command, Ui ui, QuestionsList questionsList,
            TopicList topicList, QuestionListByTopic questionListByTopic, ResultsList allResults, Helper helper,
            AnswerTracker userAnswers
    ) throws CustomException {
        String lowerCaseCommand = command.toLowerCase();
        if (ui.isPlaying) {

            if (lowerCaseCommand.startsWith("topic") && !hasChosenTopic) {
                processStartCommand(lowerCaseCommand, ui, topicList, questionListByTopic, allResults, userAnswers);
                //hasChosenTopic = true;
            } else if (lowerCaseCommand.startsWith("topic") && hasChosenTopic) {
                throw new CustomException("Please choose a topic in the format: topic [INDEX]");
            } else if (lowerCaseCommand.startsWith("bye")) {
                ui.isPlaying = false;
            } else if (lowerCaseCommand.startsWith("solution") || lowerCaseCommand.startsWith("explain")) {
                //System.out.println("ERROR");
                processSolutionCommand(lowerCaseCommand, ui, questionsList, topicList, questionListByTopic);
            } else if (lowerCaseCommand.startsWith("results")) {
                processResultsCommand(lowerCaseCommand, allResults, ui, questionListByTopic, userAnswers);
            } else if (lowerCaseCommand.startsWith("help")) {
                processHelpCommand(lowerCaseCommand, ui, helper);
            } else if (lowerCaseCommand.startsWith("list")){
                processListCommand(topicList, ui);
            } else {
                throw new CustomException("-1 HP coz invalid command");
            }
        }

    }

    private void processListCommand(TopicList topicList, Ui ui) {
        String[][] printData = topicList.listAllTopics();
        String[] tableHeader = {"index", "topic", "summary", "attempted"};
        ui.printTable(tableHeader, printData);
    }

    private void processResultsCommand(String lowerCaseCommand, ResultsList allResults, Ui ui,
                                       QuestionListByTopic questionListByTopic, AnswerTracker userAnswers)
            throws CustomException {
        if (allResults.getSizeOfAllResults() == NO_RESULTS) {
            throw new CustomException(MESSAGE_NO_RESULTS);
        }
        String[] commandParts = lowerCaseCommand.split(COMMAND_SPLITTER, TWO_PARAMETERS);
        assert commandParts.length <= TWO_PARAMETERS;
        switch (commandParts.length) {
        case (NO_PARAMETERS): {
            ui.printAllResults(!INCLUDES_DETAILS, allResults, questionListByTopic, userAnswers);
            break;
        }
        case (ONE_PARAMETER): {
            if (commandParts[PARAMETER_INDEX].equals(DETAILS_PARAMETER)) {
                ui.printAllResults(INCLUDES_DETAILS, allResults, questionListByTopic, userAnswers);
            } else {
                try {
                    int index = Integer.parseInt(commandParts[FIRST_PARAMETER]);
                    String score = allResults.getSpecifiedResult(index - 1).getScore();
                    int topicNum = allResults.getTopicNum(index - 1);
                    ui.printOneResult(!INCLUDES_DETAILS, topicNum, score, questionListByTopic, userAnswers, index);
                } catch (NumberFormatException e) {
                    throw new CustomException(MESSAGE_INVALID_PARAMETERS);
                } catch (IndexOutOfBoundsException e) {
                    throw new CustomException(MESSAGE_INDEX_OUT_OF_BOUNDS);
                }
            }
            break;
        }
        case (TWO_PARAMETERS): {
            if (!commandParts[PARAMETER_INDEX].equals(DETAILS_PARAMETER)) {
                throw new CustomException(MESSAGE_INVALID_PARAMETERS);
            }
            try {
                int index = Integer.parseInt(commandParts[SECOND_PARAMETER]);
                String score = allResults.getSpecifiedResult(index - 1).getScore();
                int topicNum = allResults.getTopicNum(index - 1);
                ui.printOneResult(INCLUDES_DETAILS, topicNum, score, questionListByTopic, userAnswers, index);
                break;
            } catch (NumberFormatException e) {
                throw new CustomException(MESSAGE_INVALID_INDEX);
            } catch (IndexOutOfBoundsException e) {
                throw new CustomException(MESSAGE_INDEX_OUT_OF_BOUNDS);
            }
        }
        default: {
            throw new CustomException(MESSAGE_ERROR);
        }
        }
    }

    private void processStartCommand (
            String lowerCaseCommand, Ui ui, TopicList topicList, QuestionListByTopic questionListByTopic,
            ResultsList allResults, AnswerTracker userAnswers
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
                throw new CustomException("No such topic");
            }
            ui.printChosenTopic(topicNum, topicList, questionListByTopic, allResults, userAnswers);
            System.out.println("You have finished the topic! What will be your next topic?");
            ui.printTopicList(topicList, ui);

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
            if (topicNum < 1 || topicNum > topicList.getSize()) {
                throw new CustomException("No such topic");
            }

            QuestionsList qnList = questionListByTopic.getQuestionSet(topicNum - 1);
            if (questionNum < 1 || questionNum > qnList.getSize()) {
                throw new CustomException(("No such question"));
            }

            if (isSolutionCommand) {
                String solution = qnList.getOneSolution(questionNum);
                ui.printOneSolution(questionNum, solution);
                return;
            } // only runs if explanation
            assert typeOfCommand.contentEquals("explain") : "typeOfCommand should be explain";
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
                                   Results topicResults, ArrayList<Boolean> correctness){
        inputAnswers[index] = answer;
        String correctAnswer = questionUnit.getSolution();
        if (answer.equals(correctAnswer)){
            topicResults.increaseCorrectAnswers();
            correctness.add(IS_CORRECT_ANSWER);
        } else {
            correctness.add(!IS_CORRECT_ANSWER);
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

