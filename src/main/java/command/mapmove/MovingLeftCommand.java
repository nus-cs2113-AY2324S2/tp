package command.mapmove;

public class MovingLeftCommand extends MapMoveCommand {

    public MovingLeftCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        for (int i = 0; i < commandModifier; i++) {
            currentMap.movePlayerLeftOne();
        }
    }
}
