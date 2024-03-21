package command.mapmove;

public class MovingForwardCommand extends MapMoveCommand {

    public MovingForwardCommand(String userInput) {
        super(userInput);
    }
    @Override
    public void execute() {
        for (int i = 0; i < commandModifier; i++) {
            currentMap.movePlayerUpOne();
        }
    }
}
