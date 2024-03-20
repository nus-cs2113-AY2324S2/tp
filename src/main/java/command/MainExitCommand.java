package command;
public class MainExitCommand implements MainCommand{

    public static boolean execute(boolean isExit) {
        System.out.println("Bye. Hope to see you again soon!");
        return true;
    }
}
