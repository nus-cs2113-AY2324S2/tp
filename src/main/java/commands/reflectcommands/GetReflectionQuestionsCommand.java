package commands.reflectcommands;

import commands.Command;
import reflection.ReflectionManager;

public class GetReflectionQuestionsCommand implements Command {

    private ReflectionManager reflectionManager;
    public GetReflectionQuestionsCommand(ReflectionManager reflectionManager) {
        this.reflectionManager = reflectionManager;
    }

    @Override
    public void execute() {
        reflectionManager.printFiveRandomQuestions();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
