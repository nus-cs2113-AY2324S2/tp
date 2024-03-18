package commands.fitnesscommands;

import commands.Command;
import exceptions.Wellness360Exception;
import fitness.FitnessMotivator;

public class GetExercisesCommand implements Command {

    private FitnessMotivator fitnessMotivator;

    public GetExercisesCommand (FitnessMotivator fitnessMotivator) {
        this.fitnessMotivator = fitnessMotivator;
    }

    @Override
    public void execute() throws Wellness360Exception {
        fitnessMotivator.getExercises();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
