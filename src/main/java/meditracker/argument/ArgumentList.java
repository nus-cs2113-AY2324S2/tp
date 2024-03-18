package meditracker.argument;

import meditracker.exception.ArgumentNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ArgumentList class for managing a list of Argument
 * Calls on ArgumentParser when parse method is invoked
 * @see ArgumentParser
 */
public class ArgumentList {
    private final List<Argument> arguments;

    /**
     * Constructs ArgumentList to take in variable length of Argument
     * Assertion test is used to check no flag collision for the
     * arguments specified
     *
     * @param arguments Arguments to be included in the list
     */
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

    /**
     * Parses user raw input arguments into ArgumentName and
     * corresponding argument value
     *
     * @param rawInput Raw input to be parsed
     * @return A map of argument name as key and the corresponding value
     * @throws ArgumentNotFoundException When argument required not found
     * @see ArgumentParser
     */
    public Map<ArgumentName, String> parse(String rawInput) throws ArgumentNotFoundException {
        ArgumentParser argumentParser = new ArgumentParser(this, rawInput);
        return argumentParser.parsedArguments;
    }

    public List<Argument> getArguments() {
        return arguments;
    }
}
