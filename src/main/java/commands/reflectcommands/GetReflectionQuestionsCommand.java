package commands.reflectcommands;

import commands.Command;
import reflection.Reflection;

public class GetReflectionQuestionsCommand implements Command {

    private Reflection reflection;
    public GetReflectionQuestionsCommand(Reflection reflection) {
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
