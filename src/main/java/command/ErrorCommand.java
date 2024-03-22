package command;

import java.lang.reflect.Executable;

public class ErrorCommand extends Command{
    Exception exception;

    public ErrorCommand(Exception exception) {
        this.exception = exception;
    }

    @Override
    public void execute() {
        // create a new exception with message "[error]\n[narration]\n[dialouge]\n[instruction]" the various [fields] can be left blank.
        String[] exceptions = exception.getMessage().split("\n", 4);
        textBox.setNextError(exceptions[0].isBlank() ? "" : exceptions[0]);
        textBox.setNextNarration(exceptions[1].isBlank() ? "" : exceptions[1]);
        textBox.setNextDialogue(exceptions[2].isBlank() ? "" : exceptions[2]);
        textBox.setNextDialogue(exceptions[3].isBlank() ? "" : exceptions[3]);
    }
}
