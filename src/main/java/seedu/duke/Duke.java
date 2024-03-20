package seedu.duke;

import seedu.duke.ui.UI;

import java.util.Objects;
import java.util.Scanner;

public class Duke {
    static Scanner in = new Scanner(System.in);
    static boolean finished = false;

    public static void setFinished(boolean b) {
        finished = b;
    }

    public static void main(String[] args) {
        UI.printGreeting();
        UserList userList = new UserList();

        //Replaced with Parser Logic
        while (!finished) {
            try {
                String input = in.nextLine();
                Parser parser = new Parser();
                parser.parseCommand(input);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
