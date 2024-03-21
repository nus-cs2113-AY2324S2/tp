package seedu.duke.command;

import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.modules.Module;

import java.util.Map;

public class RemoveCommand extends Command{
    private final Map<String, String> args;
    public RemoveCommand(Map<String, String> args){
        this.args = args;
    }
    @Override
    public void execute(String userInput) {
        String inputCourseCode = args.get("courseCode");
        assert inputCourseCode != null : "key \"courseCode\" in args should correspond to a value";
        try{
            Module moduleToRemove = moduleList.getModule(inputCourseCode);
            moduleList.removeModule(moduleToRemove);
            System.out.println("Module " + inputCourseCode + " Removed!");
        } catch (ModuleNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
