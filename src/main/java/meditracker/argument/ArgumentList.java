package meditracker.argument;

import meditracker.exception.ArgumentNotFoundException;

import java.util.List;
import java.util.Map;

public class ArgumentList {
    private final List<Argument> arguments;

    public ArgumentList(Argument... arguments) {
        this.arguments = List.of(arguments);
    }

    public Map<ArgumentName, String> parse(String rawInput) throws ArgumentNotFoundException {
        ArgumentParser argumentParser = new ArgumentParser(this, rawInput);
        return argumentParser.parsedArguments;
    }

    public List<Argument> getArguments() {
        return arguments;
    }
}
