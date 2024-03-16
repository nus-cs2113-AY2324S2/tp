package parser;

public class Parser {

    public static Command determineCommand(ReflectionTime reflectionTime, HabitTracker habitTracker,
                                           SleepTracker sleepTracker, FitnessMotivator fitnessMotivator,
                                           FocusTimer focusTimer, String userInput) {
        String[] userWords = userInput.trim().split("\\s+", 2);
        String userCommandSection = userWords[0];

        String commandArgs = userWords.length == 2 ? userWords[1] : "";

        switch (userCommandSection) {
        case "reflect":
            return determineReflectionCommand(reflectionTime, commandArgs);
        case "habit":
            return determineHabitCommand(habitTracker, commandArgs);
        case "sleep":
            return determineSleepCommand(sleepTracker, commandArgs);
        case "fitness":
            return determineFitnessCommand(fitnessMotivator, commandArgs);
        case "focus":
            return determineFocusCommand(focusTimer, commandArgs);
        case "exit":
            return new ExitCommand(commandArgs);
        default:
            throw new UnknownCommandException("Unknown command");
        }
    }
}
