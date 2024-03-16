package command.mapmove;


public class InteractingCommand extends MapMoveCommand {
    @Override
    public void execute() {
        String entityInteractedWith = currentMap.handleInteract();
    }
}
