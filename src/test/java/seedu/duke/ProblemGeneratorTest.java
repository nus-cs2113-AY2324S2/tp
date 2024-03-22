package seedu.duke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

public class ProblemGeneratorTest {
    public static String[] commands = {"generate -t + -n 1 -d 1", "generate -t - -n 2 -d 2",
                            "generate -t * -n 3 -d 3", "generate -t / -n 4 -d 4"};
    public static void operatorTest() {
        for (String command: commands) {
            ProblemGenerator pb = new ProblemGenerator();
            Test test = pb.typeChoose(command);
            ArrayList<Problem> problems = test.getProblem();
            for (Problem problem: problems) {
                if (command.equals(commands[0])) {
                    assertTrue(problem.unsolved().contains("+"), "+: Problem format is incorrect: " + problem.unsolved());
                } else if (command.equals(commands[1])) {
                    assertTrue(problem.unsolved().contains("-"),"-: Problem format is incorrect: " + problem.unsolved());
                } else if (command.equals(commands[2])) {
                    assertTrue(problem.unsolved().contains("*"), "*: Problem format is incorrect: " + problem.unsolved());
                } else if (command.equals(commands[3])) {
                    assertTrue(problem.unsolved().contains("/"), "/: Problem format is incorrect: " + problem.unsolved());
                } else {
                    fail("fail: Problem format is incorrect: " + problem.unsolved());
                }
            }
        }
    }
    private static HashMap<String, String> parseCommand(String command) {
        return ProblemGenerator.parseCommand(command);
    }

    private static int[] parseNumbers(String problem) {

        // 使用正则表达式匹配数字
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(problem);

        // 提取匹配到的数字
        int[] numbers = new int[2];
        int index = 0;
        while (matcher.find() && index < 2) {
            numbers[index] = Integer.parseInt(matcher.group());
            index++;
        }
        return numbers;
    }
    public static void numberTest() {
        for (String command: commands) {
            HashMap<String, String> parsedCommand = parseCommand(command);
            ProblemGenerator pb = new ProblemGenerator();
            Test test = pb.typeChoose(command);
            ArrayList<Problem> problems = test.getProblem();
            assertEquals(Integer.parseInt(parsedCommand.get("number")), problems.size());
        }
    }

    public static void digitTest() {
        for (String command: commands) {
            HashMap<String, String> parsedCommand = parseCommand(command);
            ProblemGenerator pb = new ProblemGenerator();
            Test test = pb.typeChoose(command);
            ArrayList<Problem> problems = test.getProblem();
            for (Problem problem: problems) {
                int[] numbers = parseNumbers(problem.unsolved());
                for (int number: numbers) {
                    assertTrue(Integer.parseInt(parsedCommand.get("maximumDigits")) >= (int) Math.log10(number) + 1);
                }
            }
        }
    }
    

    public static void main(String[] args) {
        operatorTest();
        System.out.println("Pass operatorTest!");
        numberTest();
        System.out.println("Pass numberTest!");
        digitTest();
        System.out.println("Pass digitTest!");
    }
}
