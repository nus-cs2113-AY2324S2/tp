package meditracker.argument;

import meditracker.exception.ArgumentNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArgumentList {
    private final List<Argument> arguments;

    public ArgumentList(Argument... arguments) {
        this.arguments = List.of(arguments);

        // assertion test: check for flag collisions
        Set<String> flags = new HashSet<>();
        for (Argument argument: arguments) {
            String flag = argument.getFlag();
            assert !flags.contains(flag);
            flags.add(flag);
        }
    }

    public Map<ArgumentName, String> parse(String rawInput) throws ArgumentNotFoundException {
        ArgumentParser argumentParser = new ArgumentParser(this, rawInput);
        return argumentParser.parsedArguments;
    }

    public List<Argument> getArguments() {
        return arguments;
    }
}
