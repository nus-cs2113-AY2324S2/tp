package seedu.duke;

import java.util.Scanner;

/**
 * Represents the user interface for interacting with the chatbot.
 */
public class Ui {

    private final String name;
    private final Scanner scanner = new Scanner(System.in);

    // Pre-defined sentences
    private static final String PROBLEM_FORM =
            "Please type the number and difficulty you like in following form: \n" +
                    "< generate -t [type] -n [number] -d [maximum digit] >";
    private static final String INPUT_INSTRUCTION =
            """
                    Input Instructions:\s
                    [type]: can be + - * /, you can combine any of them.\s
                    [number]: number of problem set generated\s
                    [maximum digit]: how big can the calculation be\s

                    For example: generate -t + -n 10 -d 2\s
                    -> generate 10 problems with + and - operator, and the maximum number of digits is 2 (99 max)
                    """;

    private static final String GEN_COMMAND =
            "Generate problem sets: \t" + "gen -t [type] -n [number] -d [maximum digits]";
    private static final String HELP_COMMAND =
            "Help function: \t" + "help [type], type can be 'gen'/'command'/...";
    /**
     * Creates a new Ui with the given name.
     *
     * @param name The name of the chatbot.
     */
    public Ui(String name) {
        this.name = name;
    }

    /**
     * Displays a greeting message.
     */
    public void greet() {
        this.showLine();
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;
        System.out.println(logo);
        System.out.println("Hello! I'm " + name);
        System.out.println("Type 'help' to see the instructions. \n");
        System.out.println(PROBLEM_FORM);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println("=========================");
    }

    public void help(String helpType) {
        switch (helpType) {
            case "": // by default, user asks Input Instruction
            case "gen":
            case "generate":
                System.out.println(GEN_COMMAND);
                System.out.println(INPUT_INSTRUCTION);
                break;
            case "command":
                System.out.println(HELP_COMMAND);
            default:
                break;
        }
    }

    // invalid input

    /**
     * Displays an error message for an invalid command.
     */
    public void invalidCommand() {
        System.out.println("Invalid command! Please try again.");
    }

    /**
     * Displays an error message for an invalid index.
     */
    
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }
}