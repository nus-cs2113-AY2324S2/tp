package command;

import activeedge.task.ExerciseTask;
import activeedge.ui.CommandUi;

import static activeedge.task.TaskList.tasksList;
import java.time.LocalDateTime;


/**
 * Represents a command to log exercise activities into the system.
 * It stores information about an exercise, including its name, duration and
 * the total amount of calories burnt by doing the exercise.
 */
public class LogExerciseCommand {
    protected String exerciseName;
    protected int duration;
    protected int caloriesBurnt;
    protected LocalDateTime dateTime;


    public LogExerciseCommand(String exerciseName, int duration, int caloriesBurnt, LocalDateTime dateTime) {
        this.exerciseName = exerciseName;
        this.duration = duration;
        this.caloriesBurnt = caloriesBurnt;
        this.dateTime = dateTime;
    }

    /**
     * Executes the exercise logging command.
     * It creates a new {@link ExerciseTask} with the exercise details an adds it to
     * the system's task list. After logging the exercise, it displays a confirmation message.
     *
     * @throws ActiveEdgeException if any error occurs during the execution process
     */
    public void execute() throws ActiveEdgeException {
        ExerciseTask logExercise = new ExerciseTask(exerciseName, duration, caloriesBurnt, dateTime);
        tasksList.add(logExercise);
        CommandUi.printExerciseLogMessage(logExercise);
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getDuration() {
        return duration;
    }
}
