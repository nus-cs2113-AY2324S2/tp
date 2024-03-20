package command;

public class RestCommand implements Command {
    public void execute() {
        System.out.println("RestCommand executed");
    }

    public boolean isExit() {
        return false;
    }
}
