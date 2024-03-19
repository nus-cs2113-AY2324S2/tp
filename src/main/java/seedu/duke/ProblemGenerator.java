package seedu.duke;

// import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
// import java.util.Scanner;

public class ProblemGenerator {

    static final String DEFAULT_NUMBER = "10";
    static final String DEFAULT_MAX_DIGITS = "2";

    static final String DEFAULT_OPERATORS = "+-*/";

    public void TypeChoose(String command) {
//        while (true) {
            // System.out.println(PROBLEM_FORM);
            // ui.askForInput(PROBLEM_FORM);
            // String command = in.nextLine();
            // String command = ui.readCommand();
            HashMap<String, String> parameter = parseCommand(command);
            Generate(parameter);
//        }
    }

    private void Generate(HashMap<String, String> parameter) {

        int number = Integer.parseInt(parameter.get("number"));
        int maxDigit = Integer.parseInt(parameter.get("maximumDigits"));
        String op = parameter.get("type");
        ArrayList<String> operations = new ArrayList<>();

        if (op.contains("+")) {
            operations.add("+");
        }
        if (op.contains("-")) {
            operations.add("-");
        }
        if (op.contains("*")) {
            operations.add("*");
        }
        if (op.contains("/")) {
            operations.add("/");
        }

        ArrayList<Problem> test = null;
        for (int i = 0; i < number; i++) {

            String description;
            double answer;
            int max = (int) Math.pow(10, maxDigit);
            int op1 = (int) (Math.random() * max);
            int op2 = (int) (Math.random() * max);
            String tempOperator = operations.get((int) (Math.random() * operations.size()));

            description = op1 + tempOperator + op2 + "=";
            switch (tempOperator) {
                case ("+"):
                    answer = op1 + op2;
                    break;
                case ("-"):
                    answer = op1 - op2;
                    break;
                case ("*"):
                    answer = op1 * op2;
                    break;
                case ("/"):
                    if (op2 == 0) continue;
                    answer = (double) op1 / op2;
                    break;
                default:
                    continue;
            }

            Problem p = new Problem(description, answer);
            System.out.println(p.unsolved());

            if (test == null) {
                test = new ArrayList<>();
            }
            test.add(p);

        }

    }


    public static HashMap<String, String> parseCommand(String command) {
        HashMap<String, String> options = new HashMap<>();
        String[] tokens = command.split("\\s+");

        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "-t" -> options.put("type", tokens[i + 1]);
                case "-n" -> options.put("number", tokens[i + 1]);
                case "-d" -> options.put("maximumDigit", tokens[i + 1]);
            }
        }

        DefaultOptions(command, options);

        return options;
    }

    private static void DefaultOptions(String command, HashMap<String, String> options) {
        if (!command.contains("-t")) {
            options.put("type", DEFAULT_OPERATORS);
        }
        if (!command.contains("-n")) {
            options.put("number", DEFAULT_NUMBER);
        }
        if (!command.contains("-d")) {
            options.put("maximumDigits", DEFAULT_MAX_DIGITS);
        }
    }


}
