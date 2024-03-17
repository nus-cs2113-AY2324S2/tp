package seedu.duke;

import seedu.duke.modules.ModuleList;

import java.util.Objects;
import java.util.Scanner;

public class FAP {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static ModuleList takenModuleList = new ModuleList(10);
    public static ModuleList toBeTakenModuleList = new ModuleList(10);
    public static void main(String[] args) {
        System.out.println("Hello from FAP");
        System.out.println("What is your name?");
        String command = "";
        do {
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            System.out.println("Hello " + command);
        } while (!Objects.equals(command, "bye"));
    }
}
