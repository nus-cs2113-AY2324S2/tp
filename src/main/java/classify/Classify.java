package classify;

import classify.student.StudentList;

import java.util.Scanner;

public class Classify {
    public static Scanner in = new Scanner(System.in);

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        // @@author ParthGandhiNUS
        // CAA: 9 March 2024 1pm
        Ui.printWelcomeMessage();
        // Takes in only the first word input by the user
        String[] userCommand = UserInput.processInput(in.nextLine());

        //Set up polling for the first word input by the user.
        // If user's first word is "bye", will exit the while loop.
        while (!(userCommand[0].equals("bye"))){
            Ui.printUserPrompt();
            InputParsing.parseUserCommand(userCommand, StudentList.masterStudentList, in);
            userCommand = UserInput.processInput(in.nextLine());
        }

        Ui.printEndConversation();
    }
}

