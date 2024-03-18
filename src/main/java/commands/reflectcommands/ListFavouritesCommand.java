
package commands.reflectcommands;

import commands.Command;
import reflection.ReflectionManager;

public class ListFavouritesCommand implements Command {
    private ReflectionManager reflectionManager;
    public ListFavouritesCommand(ReflectionManager reflectionManager) {
        this.reflectionManager = reflectionManager;
    }
    @Override
    public void execute() {
        reflectionManager.printFavourites();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
