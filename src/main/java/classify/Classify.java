package classify;

import classify.student.StudentList;
import classify.user.InputParsing;
import classify.user.Ui;
import classify.user.UserInput;

import java.util.Scanner;

public class Classify {
    public static Scanner in = new Scanner(System.in);

    /**
     * Main entry-point for the Classify application.
     */
    public static void main(String[] args) {

        // @@author ParthGandhiNUS
        // CAA: 9 March 2024 1pm
        Ui.printWelcomeMessage();
        Ui.printUserPrompt();
        
        // Takes in input from the user, and processes input to determine if it contains a command and a name   
        String[] userCommand = UserInput.processInput(in.nextLine());

        // Set up polling for the first word input by the user.
        // If user's first word is "bye", will exit the while loop.
        while (!(userCommand[0].equals("bye"))){
            InputParsing.parseUserCommand(userCommand, StudentList.masterStudentList, in);
            userCommand = UserInput.processInput(in.nextLine());

        }

        Ui.printEndConversation();
    }
}

