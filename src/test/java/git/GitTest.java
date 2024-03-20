package git;

import exceptions.GitException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GitTest {
    @Test
    public void executeCommand_invalidCommand_success() {
        try {
            Git git = new Git();
            String[] commandParts = {"nonsense", ""};
            git.executeCommand(commandParts);
        } catch (GitException e) {
            assertEquals("Unknown command. Type 'help' for a list of commands.", e.getMessage());;
        }
    }
}
