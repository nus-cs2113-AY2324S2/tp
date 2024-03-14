package byteceps.commands;

import java.util.ArrayList;

public class WorkoutCommand extends Command{
    public static final String COMMAND_WORD = "workout";

    public WorkoutCommand(InputArguments commandAction, ArrayList<InputArguments> additionalArguments) {
        super(commandAction, additionalArguments);
    }

    @Override
    public CommandResult execute() {
        return null; // TODO: implement Workout command
    }
}
