package seedu.duke;

import seedu.duke.ui.UI;

import java.util.Scanner;

public class Duke {
    static final Scanner IN = new Scanner(System.in);
    static boolean isFinished = false;

    public static void setIsFinished(boolean b) {
        isFinished = b;
    }

    public static void main(String[] args) {
        UI.printGreeting();
        UserList userList = new UserList();

        while (!isFinished) {
            try {
                String input = IN.nextLine();
                Parser parser = new Parser();
                parser.parseCommand(input, userList);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
