package commands.reflectcommands;

import commands.Command;
import reflection.ReflectionManager;

public class GetReflectionQuestionsCommand implements Command {

    private ReflectionManager reflection;
    public GetReflectionQuestionsCommand(ReflectionManager reflection) {
        this.reflection = reflection;
    }

    @Override
    public void execute() {
        reflection.printFiveRandomQuestions();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
