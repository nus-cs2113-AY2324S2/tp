package byteceps.commands;

import java.util.ArrayList;

public class ExerciseCommand extends Command{
    public static final String COMMAND_WORD = "exercise";
    public ExerciseCommand(InputArguments commandAction, ArrayList<InputArguments> additionalArguments) {
        super(commandAction, additionalArguments);
    }


    @Override
    public CommandResult execute() {
        switch(getAction()) {
        case "add":
            addExercise();
            break;
        case "delete":
            deleteExercise();
            break;
        default:
            throw new UnsupportedOperationException();
        }

        return new CommandResult("TO IMPLEMENT");
    }

    public void addExercise() {
        System.out.printf("Adding exercise: %s\n", getActionParameters());
    }

    public void deleteExercise() {
        System.out.printf("Deleting exercise %s\n", getActionParameters());
    }
}
