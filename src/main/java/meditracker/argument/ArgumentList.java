package meditracker.argument;

import meditracker.exception.ArgumentNotFoundException;

import java.util.List;
import java.util.Map;

public class ArgumentList {
    private final List<Argument> ARGUMENTS;

    public ArgumentList(Argument... arguments) {
        ARGUMENTS = List.of(arguments);
    }

    public Map<ArgumentName, String> parse(String rawInput) throws ArgumentNotFoundException {
        ArgumentParser argumentParser = new ArgumentParser(this, rawInput);
        return argumentParser.PARSED_ARGUMENTS;
    }

    public List<Argument> getArguments() {
        return ARGUMENTS;
    }
}
