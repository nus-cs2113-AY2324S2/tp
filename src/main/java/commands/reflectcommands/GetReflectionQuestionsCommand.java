package commands.reflectcommands;

import commands.Command;
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
     */
    public GetReflectionQuestionsCommand(ReflectionManager reflectionManager) {
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
