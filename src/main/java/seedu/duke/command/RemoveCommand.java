package seedu.duke.command;

import seedu.duke.modules.Module;

import java.util.Map;

public class RemoveCommand extends Command{
    private final Map<String, String> args;
    public RemoveCommand(Map<String, String> args){
        this.args = args;
    }
    @Override
    public void execute(String userInput) {
        Module moduleToRemove = moduleList.getModule(args.get("courseCode"));
        if(moduleToRemove == null){
            System.out.println("Module not found in your list!");
        }else{
            moduleList.removeModule(moduleToRemove);
            System.out.println("Module "+args.get("courseCode")+" Removed!");
        }
    }
}
