package parser;

import commands.Command;
import commands.ExitCommand;
import reflection.ReflectionManager;
import sleep.SleepTracker;

import static parser.ReflectionCommandParser.determineReflectionCommand;
import static parser.SleepCommandParser.determineSleepCommand;
import exceptions.Wellness360Exception;

public class Parser {

    public static Command determineCommand(SleepTracker sleepTracker,
                                           ReflectionManager reflection,
                                           String userInput)
            throws Wellness360Exception {

        String[] userWords = userInput.trim().split("\\s+", 2);
        String userCommandSection = userWords[0];

        String commandArgs = userWords.length == 2 ? userWords[1] : "";

        switch (userCommandSection) {
        case "reflect":
            return determineReflectionCommand(reflection, commandArgs);
        case "habit":
        case "sleep":
            return determineSleepCommand(sleepTracker, commandArgs);
        case "fitness":
        case "focus":
        case "exit":
            return new ExitCommand(commandArgs);
        default:
            throw new Wellness360Exception("Hello");
        }
    }
}
