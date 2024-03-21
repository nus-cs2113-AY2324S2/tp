package seedu.duke.command;


import seedu.duke.exceptions.GpaException;

public class ViewGpaCommand extends Command{
    @Override
    public void execute(String userInput) {
        try {
            System.out.println("Your current GPA is: " + moduleList.tallyGPA());
        } catch (GpaException e) {
            System.out.println(e.getMessage());
        }
    }
}
