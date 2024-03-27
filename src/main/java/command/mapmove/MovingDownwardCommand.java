package command.mapmove;

public class MovingDownwardCommand extends MapMoveCommand {
    public MovingDownwardCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        for (int i = 0; i < commandModifier; i++) {
            currentMap.movePlayerDownOne();
        }
    }
}
