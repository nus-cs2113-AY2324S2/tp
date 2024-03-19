package seedu.duke;

// import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
 import java.util.Scanner;

public class ProblemGenerator {

    static final String DEFAULT_NUMBER = "10";
    static final String DEFAULT_MAX_DIGITS = "2";

    static final String DEFAULT_OPERATORS = "+-*/";


    static final String PROBLEM_FORM = "pleas operators the number and difficulty you like in following form\n"+
            "generate -t [operators] -n [number] -d [maximum digit] \n";
    Scanner in = new Scanner(System.in);

    public Test TypeChoose() {

        System.out.println(PROBLEM_FORM);
        String command = in.nextLine();
        HashMap<String, String> parameter = parseCommand(command);

        return Generate(parameter);


    }

    private Test Generate(HashMap<String, String> parameter) {

        int number = Integer.parseInt(parameter.get("number"));

        int maxDigit =  Integer.parseInt(parameter.get("maximumDigits"));
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
        Test test = new Test(op,maxDigit,number) ;

        for( int i=0; i<number;i++){

            String desctiption = "";
            double answer;
            int max = (int) Math.pow(10,maxDigit);
            int op1 = (int) (Math.random()*max);
            int op2 = (int) (Math.random()*max);
            String tempOperator = operations.get((int) (Math.random()*operations.size()));


            switch (tempOperator){
            case("+"):
                answer = op1 + op2;
                break;
            case("-"):
                answer = op1 - op2;
                break;
            case("*"):
                answer = op1 * op2;
                break;
            case("/"):
                if(op2==0) continue;
                answer = (double) op1 / op2;
                break;
            default:
                continue;

            }
            desctiption = op1 + tempOperator + op2 + "=";



            Problem p = new Problem(desctiption,answer);
            System.out.println((i+1) +". "+ p.unsolved());
            test.addToTest(p);



        }
    return test;
    }


    public static HashMap<String, String> parseCommand(String command) {
        HashMap<String, String> options = new HashMap<>();
        String[] tokens = command.split("\\s+");

        for (int i = 0; i < tokens.length; i++) {

            if (tokens[i].equals("-t")) {
                options.put("operators", tokens[i + 1]);
            } else if (tokens[i].equals("-n")) {
                options.put("number", tokens[i + 1]);
            } else if (tokens[i].equals("-d")) {
                options.put("maximumDigits", tokens[i + 1]);

            }
        }

        DefaultOptions(command, options);

        return options;
    }


    private static void DefaultOptions(String command, HashMap<String, String> options) {
        if(!command.contains("-t")){
            options.put("operators", DEFAULT_OPERATORS);

        }
        if (!command.contains("-n")) {
            options.put("number", DEFAULT_NUMBER);
        }
        if (!command.contains("-d")) {
            options.put("maximumDigits", DEFAULT_MAX_DIGITS);
        }
    }


}
