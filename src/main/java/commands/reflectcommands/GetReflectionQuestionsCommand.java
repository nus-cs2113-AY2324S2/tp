package commands.reflectcommands;

import commands.Command;
import exceptions.ReflectException;
import reflection.ReflectionManager;

/**
 * A command implementation for retrieving reflection questions.
 */
public class GetReflectionQuestionsCommand implements Command {

    private ReflectionManager reflectionManager;

    /**
     * Constructs a GetReflectionQuestionsCommand.
     *
     * @param reflectionManager The ReflectionManager instance to be used for retrieving questions.
     * @param reflectionCommandArgs The string representing the reflection command arguments.
     * @throws ReflectException if the command arguments is not empty.
     */
    public GetReflectionQuestionsCommand(ReflectionManager reflectionManager, String reflectionCommandArgs)
            throws ReflectException {

        if (!reflectionCommandArgs.isBlank()) {
            throw new ReflectException("Additional parameters for 'reflect get' command are not allowed.");
        }
        this.reflectionManager = reflectionManager;
    }

    /**
     * Executes the command to retrieve and print five random reflection questions.
     */
    @Override
    public void execute() {
        reflectionManager.printFiveRandomQuestions();
    }

    /**
     * Determines if this command represents an exit action.
     *
     * @return Always returns false, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
