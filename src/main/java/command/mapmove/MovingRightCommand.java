package command.mapmove;


public class MovingRightCommand extends MapMoveCommand {
    public MovingRightCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        for (int i = 0; i < commandModifier; i++) {
            currentMap.movePlayerRightOne();
        }
    }
}
