package seedu.duke.command;


public class ViewGpaCommand extends Command{
    @Override
    public void execute(String userInput) {
        System.out.println("Your current GPA is: " + moduleList.tallyGPA());
    }
}
