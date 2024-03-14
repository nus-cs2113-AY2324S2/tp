package seedu.duke;

import seedu.duke.ui.UI;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        UI.printGreeting();
        UserList userList = new UserList();


        //replace this with parser
        while (true) {
            String input = in.nextLine();
            //extracts first word as command
            String command = input.contains(" ") ? input.split(" ")[0] : input;
            String description = "";
            if (input.contains(" ")) {
                description = input.split(" ", 2)[1];
            }

            if (Objects.equals(command, "switch")) {
                int index = (new Scanner(description).useDelimiter("\\D+").nextInt()) - 1;
                userList.setActiveUser(index);
                UI.printSetActiveUser(userList.getActiveUser().getName());
            } else if (Objects.equals(command, "list")) {
                UI.printListingUsers();
                userList.listAll();
            } else if (Objects.equals(command, "bye")) {
                UI.printBye();
                break;
            } else if (Objects.equals(command, "add")) {
                UI.printAddUser(description);
                User newUser = new User(description);
                userList.addUser(newUser);
            } else if (Objects.equals(command, "current")) {
                UI.printSetActiveUser(userList.getActiveUser().getName());
            } else {
                UI.printInvalidCommand();
            }
        }
    }
}
