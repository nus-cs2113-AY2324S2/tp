import commands.Command;
import exceptions.Wellness360Exception;
import parser.Parser;
import ui.Ui;
import java.util.Scanner;
import sleep.SleepTracker;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isExit = false;

    public static void main(String[] args) {

        SleepTracker sleepTracker = new SleepTracker();

        while (!isExit) {

            Ui.promptUserInput();

            String userInput = scanner.nextLine();

            //execute user command if it is valid else throw exception
            //save tasks to file after each command
            try {
                Command userCommand = Parser.determineCommand(sleepTracker, userInput);
                userCommand.execute();
                isExit = userCommand.isExit();
            } catch (Wellness360Exception e) {
                Ui.printMessageWithSepNewLine(e.getMessage());
            }
        }
    }
}
