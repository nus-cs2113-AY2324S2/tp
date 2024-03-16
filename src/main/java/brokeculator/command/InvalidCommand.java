package brokeculator.command;

public class InvalidCommand extends Command{

    public InvalidCommand() {
        super();
    }
    @Override
    public void execute() {
        System.out.println("Invalid command received");
    }
}
