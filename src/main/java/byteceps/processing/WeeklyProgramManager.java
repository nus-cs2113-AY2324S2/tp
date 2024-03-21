package byteceps.processing;

import byteceps.activities.Activity;
import byteceps.activities.Workout;
import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.ui.UserInterface;

import java.time.DayOfWeek;
import java.time.LocalDate;

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

    private int getOrdinalOfDay(String day) throws Exceptions.InvalidInput {
        switch (day) {
        case "mon":
        case "monday":
            return 0;
        case "tue":
        case "tuesday":
            return 1;
        case "wed":
        case "wednesday":
            return 2;
        case "thu":
        case "thursday":
            return 3;
        case "fri":
        case "friday":
            return 4;
        case "sat":
        case "saturday":
            return 5;
        case "sun":
        case "sunday":
            return 6;
        default:
            throw new Exceptions.InvalidInput("Not a valid day");
        }
    }

    public void execute(Parser parser) throws Exceptions.InvalidInput, Exceptions.ActivityDoesNotExists,
            Exceptions.ActivityExistsException {
        
        assert parser != null : "Parser must not be null";
        assert parser.getAction() != null : "Command action must not be null";

        if (parser.getAction().isEmpty()) {
            throw new Exceptions.InvalidInput("No action specified");
        }

        switch (parser.getAction()) {
        case "assign":
            executeAssignAction(parser);
            break;
        case "clear":
            executeClearAction(parser);
            break;
        case "today":
            executeTodayAction();
            break;
        case "list":
            executeListAction();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + parser.getAction());
        }
    }

    private void executeTodayAction() throws Exceptions.ActivityDoesNotExists {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        String currentDay=days[today.ordinal()];
        Workout todayWorkout= ((Workout) activityList.get(today.ordinal()));

        if (todayWorkout == null) {
            throw new Exceptions.ActivityDoesNotExists(
                    String.format("The %s entry for %s does not exist",
                            this.activityType, currentDay)
            );
        }

        String workoutString = todayWorkout.toString();

        String message = "Here's today's workout: " + System.lineSeparator() + today.toString()
                + System.lineSeparator() + workoutString;
        UserInterface.printMessage(message);
    }

    private void executeClearAction(Parser parser) throws Exceptions.InvalidInput {
        String day = parser.getActionParameter();
        if (day == null || day.isEmpty()) {
            activityList.replaceAll((workout) -> null);
            UserInterface.printMessage("All your workouts have been cleared from the week");
            return;
        }
        activityList.set(getOrdinalOfDay(day), null);
        UserInterface.printMessage(String.format("Your workout on %s has been cleared", day));
    }

    private void executeAssignAction(Parser parser) throws Exceptions.InvalidInput, Exceptions.ActivityDoesNotExists,
            Exceptions.ActivityExistsException{
        assert parser.getAction().equals("assign") : "Action must be assign";
        String day = parser.getAdditionalArguments("to");
        if (day == null || day.isEmpty()) {
            throw new Exceptions.InvalidInput("Week command not complete");
        }
        String workoutName = parser.getActionParameter();
        Activity workout = workoutManager.retrieve(workoutName);
        assignWorkoutToDay(workout, day.toLowerCase());
    }

    public void assignWorkoutToDay(Activity workout, String day) throws Exceptions.InvalidInput,
            Exceptions.ActivityExistsException{
        int ordinalOfDay = getOrdinalOfDay(day);
        Workout chosenDayWorkout = (Workout) activityList.get(ordinalOfDay);

        if (chosenDayWorkout != null) {
            throw new Exceptions.ActivityExistsException(String.format("Workout %s is already assigned to %s",
                    chosenDayWorkout.getActivityName(), day));
        } else {
            activityList.set(ordinalOfDay, workout);
            UserInterface.printMessage(String.format("Workout %s assigned to %s",
                    workout.getActivityName(), day));

        }
    }

    public String getActivityType(boolean plural) {
        return plural ? "weekly program" : "weekly programs";
    }

    @Override
    public void executeListAction() {
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
