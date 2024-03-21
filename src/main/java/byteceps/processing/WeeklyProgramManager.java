package byteceps.processing;

import byteceps.activities.Activity;
import byteceps.activities.Workout;
import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.ui.UserInterface;

public class WeeklyProgramManager extends ActivityManager {
    private final WorkoutManager workoutManager;
    private final String[] days = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY",
        "FRIDAY", "SATURDAY", "SUNDAY"};

    public WeeklyProgramManager(WorkoutManager workoutManager) {
        this.workoutManager = workoutManager;
        for (int i = 0; i < 7; i++) {
            activityList.add(null);
        }
    }

    public void execute(Parser parser) throws Exceptions.InvalidInput, Exceptions.ActivityDoesNotExists {
        if (parser.getAction().isEmpty()) {
            throw new Exceptions.InvalidInput("No action specified");
        }

        switch (parser.getAction()) {
        case "assign":
            String day = parser.getAdditionalArguments("to");
            if (day == null) {
                throw new Exceptions.InvalidInput("Week command not complete");
            }
            String workoutName = parser.getActionParameter();
            Activity workout = workoutManager.retrieve(workoutName);
            assignWorkoutToDay(workout, day.toLowerCase());
            UserInterface.printMessage(String.format("Workout %s assigned to %s", workoutName, day));
            break;
        case "clear":
            activityList.replaceAll(null);
            UserInterface.printMessage("Your weekly program has been cleared");
            break;
        case "today":
            //todo: retrieve the day of the week and print the appropriate workout
            break;
        case "list":
            list();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + parser.getAction());
        }
    }

    public void assignWorkoutToDay(Activity workout, String day) throws Exceptions.InvalidInput {
        switch (day) {
        case "mon":
        case "monday":
            activityList.set(0, workout);
            break;
        case "tue":
        case "tuesday":
            activityList.set(1, workout);
            break;
        case "wed":
        case "wednesday":
            activityList.set(2, workout);
            break;
        case "thu":
        case "thursday":
            activityList.set(3, workout);
            break;
        case "fri":
        case "friday":
            activityList.set(4, workout);
            break;
        case "sat":
        case "saturday":
            activityList.set(5, workout);
            break;
        case "sun":
        case "sunday":
            activityList.set(6, workout);
            break;
        default:
            throw new Exceptions.InvalidInput("Not a valid day");
        }
    }

    public String getActivityType(boolean plural) {
        return plural ? "weekly program" : "weekly programs";
    }

    @Override
    public void list() {
        StringBuilder message = new StringBuilder();
        message.append("Your workouts for the week:").append(System.lineSeparator());
        int index = 0;
        for (Activity workout : activityList) {
            message.append(String.format("\t%s: ", days[index++]));
            if (workout != null) {
                message.append(((Workout) workout).toString(1));
                message.append(System.lineSeparator());
            } else {
                message.append("Rest day");
                message.append(System.lineSeparator().repeat(2));
            }
        }
        UserInterface.printMessage(message.toString());
    }
}
