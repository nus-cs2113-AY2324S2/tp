package byteceps.command;

import java.util.HashMap;

public class Parser {
    private String command;
    private HashMap<String, String> arguments;

    public Parser() {
        command = "";
        arguments = new HashMap<>();
    }

    public String getCommandString() {
        return command;
    }

    public String getArgumentString(String key) {
        return arguments.get(key);
    }

    public void parseInput(String line) {

        int indexOfFirstSlash = line.indexOf('/');
        arguments.clear();

        if (indexOfFirstSlash == -1) {
            command = line.trim();
        } else {
            command = line.substring(0, indexOfFirstSlash);
            String[] argumentKeyValuePairs = line.substring(indexOfFirstSlash + 1).split("/");
            for (String keyValuePair : argumentKeyValuePairs) {
                String[] keyValueArray = keyValuePair.split(" ");

                if (keyValueArray.length == 2) {
                    arguments.put(keyValueArray[0], keyValueArray[1]);
                } else if (keyValueArray.length == 1) {
                    arguments.put(keyValueArray[0], "");
                }
            }
        }
    }

    public String toString() {
        return "COMMAND: " + System.lineSeparator() + command + System.lineSeparator() +
                "ARGUMENTS: " + System.lineSeparator() + arguments.toString();
    }
}
