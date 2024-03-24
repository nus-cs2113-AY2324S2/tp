package commands.fitnesscommands;


import commands.Command;
import exceptions.Wellness360Exception;
import fitness.FitnessMotivator;

public class AddExerciseCommand implements Command {

    private FitnessMotivator fitnessMotivator;
    private String commandArgs;

    public AddExerciseCommand(FitnessMotivator fitnessMotivator, String commandArgs) {
        this.fitnessMotivator = fitnessMotivator;
        this.commandArgs = commandArgs;
    }
    @Override
    public void execute() throws Wellness360Exception {
        fitnessMotivator.addExercises(commandArgs);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
