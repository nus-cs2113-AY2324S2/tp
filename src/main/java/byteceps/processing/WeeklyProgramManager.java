package byteceps.processing;

import byteceps.activities.Activity;
import byteceps.activities.Day;
import byteceps.activities.Workout;
import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.ui.UserInterface;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeeklyProgramManager extends ActivityManager {
    public static final String[] DAYS = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY",
        "FRIDAY", "SATURDAY", "SUNDAY"};
    private final WorkoutManager workoutManager;

    public WeeklyProgramManager(WorkoutManager workoutManager) {
        this.workoutManager = workoutManager;
        initializeDays();
    }

    private void initializeDays() {
        for (String day : DAYS) {
            Day newDay = new Day(day);
            newDay.setAssignedWorkout(null);
            activitySet.add(newDay);
        }
    }

    private Day getDay(String day) throws Exceptions.InvalidInput, Exceptions.ActivityDoesNotExists {
        switch (day.toLowerCase()) {
        case "mon":
        case "monday":
            return (Day) retrieve(DAYS[0]);
        case "tue":
        case "tuesday":
            return (Day) retrieve(DAYS[1]);
        case "wed":
        case "wednesday":
            return (Day) retrieve(DAYS[2]);
        case "thu":
        case "thursday":
            return (Day) retrieve(DAYS[3]);
        case "fri":
        case "friday":
            return (Day) retrieve(DAYS[4]);
        case "sat":
        case "saturday":
            return (Day) retrieve(DAYS[5]);
        case "sun":
        case "sunday":
            return (Day) retrieve(DAYS[6]);
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

    private void executeTodayAction() throws Exceptions.ActivityDoesNotExists, Exceptions.InvalidInput {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        Day currentDay = getDay(today.toString());
        Workout todaysWorkout= currentDay.getAssignedWorkout();

        try {
            if (todaysWorkout == null) {
                throw new Exceptions.ActivityDoesNotExists(
                        String.format("There is no workout assigned today (%s)",
                                currentDay.getActivityName())
                );
            }
            String workoutString = todaysWorkout.getExerciseList().toString();
            String message = String.format("Here's today's workout: %s%s%s%s",
                    System.lineSeparator(), currentDay.getActivityName(),
                    System.lineSeparator(), workoutString);
            UserInterface.printMessage(message);

        } catch (Exceptions.ActivityDoesNotExists e) {
            // catch so that it does not show error
            UserInterface.printMessage(e.getMessage());
        }
    }

    private void executeClearAction(Parser parser) throws Exceptions.InvalidInput, Exceptions.ActivityDoesNotExists {
        String day = parser.getActionParameter();
        if (day == null || day.isEmpty()) {
            activitySet.clear();
            initializeDays();
            UserInterface.printMessage("All your workouts have been cleared from the week");
            return;
        }
        Day currentDay = getDay(day);
        String currentDayString = currentDay.getActivityName();

        activitySet.remove(getDay(day));
        Day newDay = new Day(currentDayString);
        newDay.setAssignedWorkout(null);
        activitySet.add(newDay);

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
        assignWorkoutToDay(workout, day, false);
    }

    public void assignWorkoutToDay(Activity workout, String day, boolean fromStorageLoad)
            throws Exceptions.InvalidInput, Exceptions.ActivityExistsException,
            Exceptions.ActivityDoesNotExists {
        Day selectedDay = getDay(day);
        Workout chosenDayWorkout = selectedDay.getAssignedWorkout();

        if (chosenDayWorkout != null) {
            throw new Exceptions.ActivityExistsException(
                    String.format("Workout %s is already assigned to %s. Please unassign it first.",
                    chosenDayWorkout.getActivityName(), selectedDay.getActivityName()
                    )
            );
        }

        selectedDay.setAssignedWorkout((Workout) workout);
        if (!fromStorageLoad) {
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
        for (String day : DAYS) {
            try {
                Day dayObj = getDay(day);
                String dayString = dayObj.getActivityName();
                Workout dayWorkout = dayObj.getAssignedWorkout();
                message.append(String.format("\t%s: ", dayString));

                if (dayWorkout == null) {
                    message.append("Rest day").append(System.lineSeparator().repeat(2));
                } else {
                    message.append(dayWorkout.toString(1)).append(System.lineSeparator());
                }

            } catch (Exceptions.InvalidInput | Exceptions.ActivityDoesNotExists ignored) {
                // should not get an exception as it is generated
            }

        }
        UserInterface.printMessage(message.toString());
    }

    public JSONObject exportToJSON() {
        JSONObject json = new JSONObject();
        try {
            for (String day : DAYS) {
                Day currentDay = getDay(day);
                Workout assignedWorkout = currentDay.getAssignedWorkout();
                String workoutName = "";

                if (assignedWorkout != null) {
                    workoutName = assignedWorkout.getActivityName();
                }

                json.put(day, workoutName);
            }
        } catch (Exceptions.InvalidInput | Exceptions.ActivityDoesNotExists ignored) {
            // should not get an exception as it is generated
        }

        return json;
    }
}
