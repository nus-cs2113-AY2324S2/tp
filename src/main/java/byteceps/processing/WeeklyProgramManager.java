package byteceps.processing;

import byteceps.activities.Day;
import byteceps.activities.Workout;
import byteceps.activities.Exercise;
import byteceps.activities.TrackedWorkout;
import byteceps.activities.Activity;
import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.ui.UserInterface;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;

public class WeeklyProgramManager extends ActivityManager {
    public static final String[] DAYS = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
    private final ExerciseManager exerciseManager;
    private final WorkoutManager workoutManager;
    private final TrackedWorkoutsManager trackedWorkoutsManager;

    public WeeklyProgramManager(ExerciseManager exerciseManager, WorkoutManager workoutManager,
                                TrackedWorkoutsManager trackedWorkoutsManager) {
        this.exerciseManager = exerciseManager;
        this.workoutManager = workoutManager;
        this.trackedWorkoutsManager = trackedWorkoutsManager;
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
        case "tues":
        case "tuesday":
            return (Day) retrieve(DAYS[1]);
        case "wed":
        case "wednesday":
            return (Day) retrieve(DAYS[2]);
        case "thu":
        case "thurs":
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
        case "track":
            executeTrackAction(parser);
            break;
        case "list":
            executeListAction();
            break;
        case "history":
            executeHistoryAction(parser);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + parser.getAction());
        }
    }

    private void executeAssignAction(Parser parser) throws Exceptions.InvalidInput, Exceptions.ActivityDoesNotExists,
            Exceptions.ActivityExistsException {
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
                    String.format("Workout %s is already assigned to %s. Please clear it first.",
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

    private Day getDayFromDate(LocalDate date) throws Exceptions.ActivityDoesNotExists, Exceptions.InvalidInput {
        DayOfWeek dayFromDate = date.getDayOfWeek();
        return getDay(dayFromDate.toString());
    }

    private Day getDayFromDate(String dateString) throws Exceptions.ActivityDoesNotExists,
            Exceptions.InvalidInput, DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
        formatter = formatter.withLocale(formatter.getLocale());
        LocalDate date = LocalDate.parse(dateString, formatter);
        DayOfWeek dayFromDate = date.getDayOfWeek();
        return getDay(dayFromDate.toString());
    }

    private void executeTrackAction(Parser parser)
            throws Exceptions.InvalidInput, Exceptions.ActivityDoesNotExists {
        if (!parser.hasAdditionalArguments() || parser.getAdditionalArgumentsLength() < 3) {
            throw new Exceptions.InvalidInput("track command not complete");
        }

        String exerciseName = parser.getActionParameter();
        String sets = parser.getAdditionalArguments("sets");
        String repetition = parser.getAdditionalArguments("reps");
        String weight = parser.getAdditionalArguments("weight");
        String workoutDate = parser.getAdditionalArguments("date");

        if (exerciseName.isBlank() || sets.isBlank() || repetition.isBlank() || weight.isBlank()) {
            throw new Exceptions.InvalidInput("track command not complete");
        }

        if (exerciseManager.doesNotHaveActivity(exerciseName)) {
            throw new Exceptions.ActivityDoesNotExists(
                    String.format("The exercise %s does not exist", exerciseName)
            );
        }

        Day selectedDay;
        if (workoutDate.isBlank()) {
            workoutDate = LocalDate.now().toString();
            selectedDay = getDayFromDate(workoutDate);
        } else {
            try {
                selectedDay = getDayFromDate(workoutDate);
            } catch (DateTimeParseException e) {
                throw new Exceptions.InvalidInput("The date must be in the format yyyy-mm-dd!");
            }
        }

        String workoutName = getWorkoutName(selectedDay, workoutDate);
        trackedWorkoutsManager.addTrackedWorkout(workoutDate, workoutName);
        trackedWorkoutsManager.addTrackedExercise(workoutDate, exerciseName, weight, sets, repetition);
        UserInterface.printMessage(
                String.format("Successfully tracked %s with %s sets and %s reps",
                        exerciseName, sets, repetition)
        );
    }

    private static String getWorkoutName(Day selectedDay, String workoutDate) throws Exceptions.ActivityDoesNotExists {
        Workout assignedWorkout = selectedDay.getAssignedWorkout();
        if (assignedWorkout == null) {
            throw new Exceptions.ActivityDoesNotExists(
                    String.format(
                            "There does not seem to be a workout assigned to the date %s (day: %s). " +
                                    "Please assign one first!", workoutDate, selectedDay.getActivityName())
            );
        }

        return assignedWorkout.getActivityName();
    }

    private void executeTodayAction() throws Exceptions.ActivityDoesNotExists, Exceptions.InvalidInput {
        LocalDate currentDate = LocalDate.now();
        Day today = getDayFromDate(currentDate);
        Workout todaysWorkout = today.getAssignedWorkout();
        String todayDate = currentDate.toString();

        listGivenWorkout(todaysWorkout, todayDate, today);
    }

    private void listGivenWorkout(Workout givenWorkout, String workoutDate, Day workoutDay) {
        try {
            if (givenWorkout == null) {
                throw new Exceptions.ActivityDoesNotExists(
                        String.format("There is no workout assigned today (%s)",
                                workoutDay.getActivityName())
                );
            }
            String workoutName = workoutDay.getAssignedWorkout().getActivityName();
            HashSet<Exercise> workoutHashSet = givenWorkout.getExerciseSet();
            trackedWorkoutsManager.addTrackedWorkout(workoutDate, workoutName);
            trackedWorkoutsManager.list(workoutDate, workoutHashSet);

        } catch (Exceptions.ActivityDoesNotExists e) {
            // catch so that it does not show error
            UserInterface.printMessage(e.getMessage());
        }
    }

    private void executeHistoryAction(Parser parser) throws Exceptions.ActivityDoesNotExists, Exceptions.InvalidInput {
        String parameter = parser.getActionParameter();
        if (parameter.isBlank()) {
            listHistory();
            return;
        }

        String workoutDate = parser.getActionParameter();
        // retrieve tracked workout from given date

        TrackedWorkout retrievedWorkout = (TrackedWorkout) trackedWorkoutsManager.retrieve(workoutDate);
        Day day = getDayFromDate(workoutDate);
        listGivenWorkout(retrievedWorkout, workoutDate, day);
    }

    private void listHistory() {
        trackedWorkoutsManager.executeListAction();
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

    @Override
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

}
