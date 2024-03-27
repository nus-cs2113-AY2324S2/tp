package seedu.binbash.ui;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.EndOfFileException;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import java.util.logging.Level;

public class TextIn {
    private static final Logger TEXTINLOGGER = Logger.getLogger("TextIn");
    private static LineReader input;

    public TextIn() {
        System.setProperty("org.jline.terminal.exec.redirectPipeCreationMode", "native");
        TEXTINLOGGER.setLevel(Level.WARNING);
        try {
            Terminal userTerminal = TerminalBuilder.builder()
                .system(true)
                .dumb(true) // TODO: omit and catch using logger
                .build();
            input = LineReaderBuilder.builder()
                .terminal(userTerminal)
                .completer(new CommandCompleter())
                .build();
        } catch (IOException e) {
            TEXTINLOGGER.log(Level.WARNING, "failed to get system terminal!");
            throw new RuntimeException(e);
        }
    }

    public PrintWriter getPrintWriter() {
        return input.getTerminal().writer();
    }

    public String nextLine() {
        try {
            String userInput = input.readLine("");
            TEXTINLOGGER.log(Level.INFO, "received raw user input: " + userInput);
            return userInput;
        } catch (EndOfFileException | UserInterruptException e) {
            TEXTINLOGGER.log(Level.INFO, "received EOF / interrupt exception");
            return "bye";
        }
    }
}
