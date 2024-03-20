package command;

public class ExerciseCommand implements Command {
    public void execute() {
        System.out.println("ExerciseCommand executed");
    }

    public boolean isExit() {
        return false;
    }
}
