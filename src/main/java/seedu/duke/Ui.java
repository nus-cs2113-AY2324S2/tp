package seedu.duke;

import seedu.duke.CommandList;
import seedu.duke.Formatter;
import seedu.duke.Parser;
import seedu.duke.SyntaxAnalyser;

import seedu.duke.exception.ProcessInputException;
import seedu.duke.exception.ArgumentMismatchException;
import seedu.duke.exception.BadTokenException;
import seedu.duke.exception.IllegalCommandException;

import java.util.Scanner;

public class Ui {
    private static String userInput;
    private static Parser userCommandReader;
    private static final Scanner IN = new Scanner(System.in);

    public void printGreet(){
        print("WELCOME BACK");

    }

    public void printBye(){
        print("See you next time on court.");
    }
    private static void print(String thingToPrint){
        System.out.println("    " + "-----NUSFC24-----");
        System.out.println("    " + thingToPrint);
        System.out.println("    " + "-----------------");
    }

    public void processInput() throws ProcessInputException {
        try {
            userCommandReader = new Parser(userInput);
        } catch (IllegalCommandException e) {
            Formatter.printErrorWrongCommand();
            throw new ProcessInputException();
        } catch (ArgumentMismatchException e1) {
            int userArgumentCount = e1.userArgumentCount;
            int correctArgumentCount = SyntaxAnalyser.getArgumentCount(e1.userCommandName);
            Formatter.printErrorArgumentsMismatch(e1.userCommandName, userArgumentCount, correctArgumentCount);
            throw new ProcessInputException();
        } catch (BadTokenException e2) {
            Formatter.printErrorBadTokens();
            throw new ProcessInputException();
        }
    }
    public void userInputCatcher() {
        userInput = IN.nextLine();
    }
}
