package seedu.duke;

import seedu.duke.ui.UI;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    static final Scanner IN = new Scanner(System.in);
    static boolean isFinished = false;

    public static void setIsFinished(boolean b) {
        isFinished = b;
    }

    public static void main(String[] args) throws FileNotFoundException {
        UI.printGreeting();
        UserList userList = new UserList();
        Storage.createFolder();
        Storage.addExistingUsers(userList);

        while (!isFinished) {
            try {
                String input = IN.nextLine();
                Parser parser = new Parser();
                parser.parseCommand(input, userList);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        //assert false: "dummy assertion set to fail";
    }

}
