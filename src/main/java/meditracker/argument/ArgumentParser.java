package meditracker.argument;

import meditracker.exception.ArgumentNotFoundException;
import meditracker.exception.DuplicateArgumentFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * ArgumentParser class to handle parsing of user input
 * All arguments have to be specified with a flag.
 */
class ArgumentParser {
    protected final Map<ArgumentName, String> parsedArguments = new HashMap<>();

    /**
     * Constructs ArgumentParser that parses raw input into corresponding key value pairs
     *
     * @param argumentList List of argument
     * @param rawInput Raw input to be parsed
     * @throws ArgumentNotFoundException Argument flag specified not found
     */
    public ArgumentParser(ArgumentList argumentList, String rawInput)
            throws ArgumentNotFoundException, DuplicateArgumentFoundException {
        List<String> rawInputSplit = List.of(rawInput.split(" "));
        SortedMap<Integer, Argument> indexes = ArgumentParser.getArgumentIndexes(argumentList, rawInputSplit);
        getArgumentValues(indexes, rawInputSplit);
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
        return String.join(" ", argContentList).strip();
    }

    /**
     * Obtains the argument index from the raw input list
     *
     * @param rawInputSplit List of raw input split by spaces
     * @param flag Argument flag to index
     * @return Index of the argument flag
     * @throws DuplicateArgumentFoundException Duplicate argument flag found
     */
    private static int getArgumentIndex(List<String> rawInputSplit,
                                        String flag)
            throws DuplicateArgumentFoundException {
        int firstFlagIndex = rawInputSplit.indexOf(flag);
        int lastFlagIndex = rawInputSplit.lastIndexOf(flag);

        if (firstFlagIndex != lastFlagIndex) {
            String errorContext = String.format("Duplicate \"%s\" argument found", flag);
            throw new DuplicateArgumentFoundException(errorContext);
        }
        return firstFlagIndex;
    }

    /**
     * Sorts a list of argument flags and their corresponding indexes
     *
     * @param rawInputSplit List of raw input split by spaces
     * @return A sorted map of arguments and their corresponding indexes
     * @throws ArgumentNotFoundException Argument flag specified not found
     * @throws DuplicateArgumentFoundException Duplicate argument found
     */
    //@@author wenenhoe-reused
    //Reused from https://github.com/wenenhoe/ip with minor modifications
    private static SortedMap<Integer, Argument> getArgumentIndexes(ArgumentList argumentList,
                                                                   List<String> rawInputSplit)
            throws ArgumentNotFoundException, DuplicateArgumentFoundException {
        SortedMap<Integer, Argument> indexes = new TreeMap<>();
        for (Argument argument: argumentList.getArguments()) {
            String flag = argument.getFlag();
            boolean isRequired = !argument.isOptional();

            int flagIndex = ArgumentParser.getArgumentIndex(rawInputSplit, flag);
            boolean isNotFound = flagIndex == -1;

            if (!isNotFound) {
                indexes.put(flagIndex, argument);
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
     * of the argument flags indexes.
     *
     * @param indexes A sorted map of arguments and their corresponding indexes
     */
    //@@author wenenhoe-reused
    //Reused from https://github.com/wenenhoe/ip with modifications to support
    //arguments without corresponding value
    private void getArgumentValues(SortedMap<Integer, Argument> indexes, List<String> rawInputSplit) {
        Argument argument = indexes.get(indexes.firstKey());
        ArgumentName argKey = argument.getName();
        boolean hasNoValue = argument.hasNoValue();
        int startIndex = indexes.firstKey() + 1; // position after argument flag
        int endIndex;

        boolean isSkipFirst = false;
        for (Map.Entry<Integer, Argument> index: indexes.entrySet()) {
            if (!isSkipFirst) {
                isSkipFirst = true; // Skips first map entry
                continue;
            }

            endIndex = index.getKey();
            String argValue;
            if (hasNoValue) {
                argValue = ""; // No value to be stored
            } else {
                argValue = ArgumentParser.getArgumentValue(rawInputSplit, startIndex, endIndex);
            }
            parsedArguments.put(argKey, argValue);

            argument = index.getValue();
            argKey = argument.getName();
            hasNoValue = argument.hasNoValue();
            startIndex = endIndex + 1; // position after argument flag
        }

        endIndex = rawInputSplit.size();
        String argValue = ArgumentParser.getArgumentValue(rawInputSplit, startIndex, endIndex);
        parsedArguments.put(argKey, argValue);
    }
}
