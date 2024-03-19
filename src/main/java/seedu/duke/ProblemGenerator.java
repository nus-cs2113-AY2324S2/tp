package seedu.duke;

// import java.util.Random;

import java.util.ArrayList;
import java.util.HashMap;

public class ProblemGenerator {

    static final String DEFAULT_NUMBER = "10";
    static final String DEFAULT_MAX_DIGITS = "2";

    static final String DEFAULT_OPERATORS = "+-*/";

    public static HashMap<String, String> parseCommand(String command) {
        HashMap<String, String> options = new HashMap<>();
        String[] tokens = command.split("\\s+");

        for (int i = 0; i < tokens.length; i++) {

            switch (tokens[i]) {
            case "-t":
                options.put("operators", tokens[i + 1]);
                break;
            case "-n":
                options.put("number", tokens[i + 1]);
                break;
            case "-d":
                options.put("maximumDigits", tokens[i + 1]);
                break;
            }
        }
        defaultOptions(command, options);
        return options;
    }

    private static void defaultOptions(String command, HashMap<String, String> options) {
        if (!command.contains("-t")) {
            options.put("operators", DEFAULT_OPERATORS);
        }
        if (!command.contains("-n")) {
            options.put("number", DEFAULT_NUMBER);
        }
        if (!command.contains("-d")) {
            options.put("maximumDigits", DEFAULT_MAX_DIGITS);
        }
    }

    public void typeChoose(String command) {
        HashMap<String, String> parameter = parseCommand(command);
        generate(parameter);
    }

    private void generate(HashMap<String, String> parameter) {

        int number = Integer.parseInt(parameter.get("number"));

        int maxDigit = Integer.parseInt(parameter.get("maximumDigits"));
        String op = parameter.get("operators");

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


        //ArrayList<Problem> test = new ArrayList<>();
        Test test = new Test(op, maxDigit, number);

        for (int i = 0; i < number; i++) {

            String description;
            double answer;
            int max = (int) Math.pow(10, maxDigit);
            int op1 = (int) (Math.random() * max);
            int op2 = (int) (Math.random() * max);
            String tempOperator = operations.get((int) (Math.random() * operations.size()));


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
                if (op2 == 0) {
                    continue;
                }
                answer = (double) op1 / op2;
                break;
            default:
                continue;

            }
            description = op1 + tempOperator + op2 + "=";


            Problem p = new Problem(description, answer);
            System.out.println((i + 1) + ". " + p.unsolved());
            test.addToTest(p);


        }

    }


}
