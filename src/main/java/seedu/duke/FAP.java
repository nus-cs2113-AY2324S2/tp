package seedu.duke;

import java.util.Objects;
import java.util.Scanner;

public class FAP {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        System.out.println("What is your name?");
//
//        Scanner in = new Scanner(System.in);
//        System.out.println("Hello " + in.nextLine());
//    }

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
    //getUserCommand()
    //printGreeting()
    //printExit()
    ////
}
