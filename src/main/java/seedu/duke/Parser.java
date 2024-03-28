package seedu.duke;

import seedu.duke.exceptions.CustomException;

import java.util.ArrayList;

public class Parser {
    private static final int NO_RESULTS = 0;
    private static final int NO_PARAMETER_LENGTH = 1;
    private static final int ONE_PARAMETER_LENGTH = 2;
    private static final int TWO_PARAMETER_LENGTH = 3;
    private static final int FIRST_PARAMETER = 1;
    private static final int SECOND_PARAMETER = 2;
    private static final String DUMMY_QUESTION_PARAMETER = "1";

    private static final String COMMAND_SPLITTER = " ";

    private static final String DETAILS_PARAMETER = "details";

    private static final String MESSAGE_NO_RESULTS = "There are no results.";
    private static final String MESSAGE_ERROR = "An error has occurred.";
    private static final String MESSAGE_INVALID_PARAMETERS = "Invalid parameters.";
    private static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "Index is out of bounds.";
    private static final String MESSAGE_INVALID_INDEX = "Index must be an integer.";
    private static final boolean INCLUDES_DETAILS = true;
    private static final boolean IS_CORRECT_ANSWER = true;


    public void parseCommand(

            String command, Ui ui, TopicList topicList, QuestionListByTopic questionListByTopic,
            ResultsList allResults, Helper helper, AnswerTracker userAnswers
    ) throws CustomException {

        String lowerCaseCommand = command.toLowerCase();
        if (ui.isPlaying) {

            if (lowerCaseCommand.contentEquals("topic")) {
                processStartCommand(lowerCaseCommand, ui, topicList, questionListByTopic, allResults, userAnswers);
            } else if (lowerCaseCommand.contentEquals("bye")) {
                ui.isPlaying = false;
            } else if (lowerCaseCommand.contentEquals("solution") || lowerCaseCommand.contentEquals("explain")) {
                processSolutionCommand(lowerCaseCommand, ui, topicList, questionListByTopic);
            } else if (lowerCaseCommand.contentEquals("results")) {
                processResultsCommand(lowerCaseCommand, allResults, ui, questionListByTopic, userAnswers);
            } else if (lowerCaseCommand.contentEquals("help")) {
                processHelpCommand(lowerCaseCommand, ui, helper);
            } else if (lowerCaseCommand.contentEquals("list")) {
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
        String[] commandParts = lowerCaseCommand.split(COMMAND_SPLITTER, TWO_PARAMETER_LENGTH);
        assert commandParts.length <= TWO_PARAMETER_LENGTH;
        switch (commandParts.length) {
        case (NO_PARAMETER_LENGTH):
            ui.printAllResults(!INCLUDES_DETAILS, allResults, questionListByTopic, userAnswers);
            break;
        case (ONE_PARAMETER_LENGTH):
            if (commandParts[FIRST_PARAMETER].equals(DETAILS_PARAMETER)) {
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
        case (TWO_PARAMETER_LENGTH):
            if (!commandParts[FIRST_PARAMETER].equals(DETAILS_PARAMETER)) {
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
        default:
            throw new CustomException(MESSAGE_ERROR);
        }
    }

    private void processStartCommand(
            String lowerCaseCommand, Ui ui, TopicList topicList, QuestionListByTopic questionListByTopic,
            ResultsList allResults, AnswerTracker userAnswers
    ) throws CustomException {
        assert (topicList.getSize() != NO_RESULTS) : "Size of topicList should never be 0";

        String[] commandParts = lowerCaseCommand.split(COMMAND_SPLITTER);
        if (commandParts.length != 2) {
            throw new CustomException("invalid " + lowerCaseCommand + " command");
        }
        String commandParameter = commandParts[FIRST_PARAMETER];
        try {
            // if parameter is an Integer
            int topicNum = Integer.parseInt(commandParameter);
            // checks validity of parameter
            if (topicNum < 1 || topicNum > topicList.getSize() + 1) {
                throw new CustomException("No such topic");
            }
            // checks if user wants a random topic num
            final int randomTopicNum = topicList.getSize() + 1;
            if (topicNum == randomTopicNum) {
                Helper helper = new Helper();
                topicNum = helper.generateRandomNumber(randomTopicNum);
            }
            assert (topicNum != 0) : "topicNum should not be 0";
            assert (topicNum != randomTopicNum) : "topicNum should not be randomTopicNum";

            // prints questions
            ui.printChosenTopic(topicNum, topicList, questionListByTopic, allResults, userAnswers);
            System.out.println("You have finished the topic! What will be your next topic?");
            topicList.get(topicNum - 1).markAsAttempted();
            ui.printTopicList(topicList, ui);

        } catch (NumberFormatException e) {
            throw new CustomException("invalid " + lowerCaseCommand + " parameter");
        }

    }

    // solution and explain commands
    private void processSolutionCommand(
            String lowerCaseCommand, Ui ui, TopicList topicList, QuestionListByTopic questionListByTopic)
            throws CustomException {
        assert (lowerCaseCommand.contentEquals("solution") || lowerCaseCommand.contentEquals("explain"))
                : "either solution or explain command";

        boolean isSolutionCommand = lowerCaseCommand.contentEquals("solution");
        String typeOfCommand = isSolutionCommand ? "solution" : "explain";

        String[] commandParts = lowerCaseCommand.split(COMMAND_SPLITTER);
        int commandPartsLength = commandParts.length;

        // checks correct number of parameters (1 or 2 only)
        if (commandPartsLength == NO_PARAMETER_LENGTH || commandPartsLength > TWO_PARAMETER_LENGTH) {
            throw new CustomException(MESSAGE_INVALID_PARAMETERS);
        }
        assert (commandPartsLength == ONE_PARAMETER_LENGTH || commandPartsLength == TWO_PARAMETER_LENGTH);

        boolean hasTwoParameters = (commandPartsLength == TWO_PARAMETER_LENGTH);

        String commandParameterTopic = commandParts[FIRST_PARAMETER];
        String commandParameterQn = hasTwoParameters ? commandParts[SECOND_PARAMETER] : DUMMY_QUESTION_PARAMETER;
        int topicNum;
        int questionNum;
        try {
            topicNum = Integer.parseInt(commandParameterTopic);
            questionNum = Integer.parseInt(commandParameterQn);
        } catch (NumberFormatException e) {
            throw new CustomException(MESSAGE_INVALID_PARAMETERS);
        }
        // checks validity of topicNum
        if (topicNum < 1 || topicNum > topicList.getSize()) {
            throw new CustomException("No such topic");
        }
        QuestionsList qnList = questionListByTopic.getQuestionSet(topicNum - 1);

        // checks validity of questionNum
        if (questionNum < 1 || questionNum > qnList.getSize()) {
            throw new CustomException(("No such question"));
        }

        if (isSolutionCommand) {
            if (!hasTwoParameters) {
                // get all solutions
                String allSolutions = qnList.getAllSolutions();
                ui.printAllSolutions(allSolutions);
                return;
            }
            // get specific solution
            String solution = qnList.getOneSolution(questionNum);
            if (!topicList.get(topicNum - 1).hasAttempted()) {
                ui.printNoSolutionAccess(); // has not attempted
            }
            ui.printOneSolution(questionNum, solution);
            return;
        }
        // only runs code below if explain command
        assert typeOfCommand.contentEquals("explain");
        if (hasTwoParameters) { // explain command only has 1 valid parameter
            throw new CustomException(MESSAGE_INVALID_PARAMETERS);
        }

        String explanation = qnList.getOneExplanation(questionNum);
        if (!topicList.get(topicNum - 1).hasAttempted()) {
            ui.printNoSolutionAccess(); // has not attempted
        }
        ui.printOneSolution(questionNum, explanation);
    }

    public void handleAnswerInputs(String[] inputAnswers, int index, String answer, Question questionUnit,
                                   Results topicResults, ArrayList<Boolean> correctness) {

        inputAnswers[index] = answer;
        String correctAnswer = questionUnit.getSolution();
        if (answer.equals(correctAnswer)) {
            topicResults.increaseCorrectAnswers();
            correctness.add(IS_CORRECT_ANSWER);
        } else {
            correctness.add(!IS_CORRECT_ANSWER);
        }
    }

    private void processHelpCommand(String lowerCaseCommand, Ui ui, Helper helper) throws CustomException {
        String[] commandParts = lowerCaseCommand.split(COMMAND_SPLITTER);
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

