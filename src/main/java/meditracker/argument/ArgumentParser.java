package meditracker.argument;

import meditracker.exception.ArgumentNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

class ArgumentParser {
    protected final Map<ArgumentName, String> parsedArguments = new HashMap<>();

    /**
     * Constructs ArgumentParser that parses raw input into corresponding key value pairs
     *
     * @param argumentList List of argument
     * @param rawInput Raw input to be parsed
     * @throws ArgumentNotFoundException Argument flag specified not found
     */
    public ArgumentParser(ArgumentList argumentList, String rawInput) throws ArgumentNotFoundException {
        List<String> rawInputSplit = List.of(rawInput.split(" "));
        SortedMap<Integer, ArgumentName> indexes = getArgumentIndexes(argumentList, rawInputSplit);
        getArgumentValues(indexes, rawInputSplit);
    }

    /**
     * Sorts a list of argument flags and their corresponding indexes
     *
     * @param rawInputSplit List of raw input split by spaces
     * @return A sorted map of arguments and their corresponding indexes
     * @throws ArgumentNotFoundException Argument flag specified not found
     */
    //@@author wenenhoe-reused
    //Reused from https://github.com/wenenhoe/ip with minor modifications
    private SortedMap<Integer, ArgumentName> getArgumentIndexes(ArgumentList argumentList, List<String> rawInputSplit)
            throws ArgumentNotFoundException {
        SortedMap<Integer, ArgumentName> indexes = new TreeMap<>();
        for (Argument argument: argumentList.getArguments()) {
            String flag = argument.getFlag();
            ArgumentName argumentName = argument.getName();
            boolean isRequired = !argument.isOptional();

            int flagIndex = rawInputSplit.indexOf(flag);
            boolean isNotFound = flagIndex == -1;

            if (!isNotFound) {
                indexes.put(flagIndex, argumentName);
            } else if (isRequired) {
                // arg keyword not found in additional input
                String errorContext = String.format("Missing \"%s\" argument", flag);
                throw new ArgumentNotFoundException(errorContext);
            }
        }
        return indexes;
    }

    /**
     * Obtains a map of argument flags and their corresponding value, using a sorted ordering
     * of the argument flags.
     *
     * @param indexes A sorted map of arguments and their corresponding indexes
     */
    //@@author wenenhoe-reused
    //Reused from https://github.com/wenenhoe/ip with minor modifications
    private void getArgumentValues(SortedMap<Integer, ArgumentName> indexes, List<String> rawInputSplit) {
        ArgumentName argKey = indexes.get(indexes.firstKey());
        int startIndex = indexes.firstKey() + 1; // position after keyword arg
        int endIndex;

        boolean isSkipFirst = false;
        for (Map.Entry<Integer, ArgumentName> index: indexes.entrySet()) {
            if (!isSkipFirst) {
                isSkipFirst = true; // Skips first map entry
                continue;
            }

            endIndex = index.getKey();
            String argValue = ArgumentParser.getArgumentValue(rawInputSplit, startIndex, endIndex);
            parsedArguments.put(argKey, argValue);

            argKey = index.getValue();
            startIndex = endIndex + 1;
        }

        endIndex = rawInputSplit.size();
        String argValue = ArgumentParser.getArgumentValue(rawInputSplit, startIndex, endIndex);
        parsedArguments.put(argKey, argValue);
    }

    /**
     * Obtains argument value using start and end index of the raw input list
     *
     * @param rawInputSplit List of raw input split by spaces
     * @param startIndex Start index in rawInputSplit of argument value
     * @param endIndex End index in rawInputSplit of argument value
     * @return Corresponding argument value, joined with spaces
     */
    private static String getArgumentValue(List<String> rawInputSplit, int startIndex, int endIndex) {
        List<String> argContentList = rawInputSplit.subList(startIndex, endIndex);
        return String.join(" ", argContentList);
    }
}
