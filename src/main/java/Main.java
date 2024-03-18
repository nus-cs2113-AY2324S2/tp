import commands.Command;
import exceptions.Wellness360Exception;
import focus.FocusTimer;
import habit.HabitTracker;
import parser.Parser;
import reflection.ReflectionManager;
import ui.Ui;
import java.util.Scanner;
import sleep.SleepTracker;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isExit = false;

    public static void main(String[] args) {

        SleepTracker sleepTracker = new SleepTracker();
        ReflectionManager reflectionManager = new ReflectionManager();
        HabitTracker habitTracker = new HabitTracker();
        FocusTimer focusTimer = new FocusTimer();

        Ui.greetUser();

        while (!isExit) {

            Ui.promptUserInput();

            String userInput = scanner.nextLine();

            //execute user command if it is valid else throw exception
            //save tasks to file after each command
            try {
                Command userCommand = Parser.determineCommand(sleepTracker, reflectionManager,
                        habitTracker, focusTimer, userInput);
                userCommand.execute();
                isExit = userCommand.isExit();
            } catch (Wellness360Exception e) {
                Ui.printMessageWithSepNewLine(e.getMessage());
            }
        }

        Ui.sayGoodbye();
    }
}
