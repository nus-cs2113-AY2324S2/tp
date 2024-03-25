package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ParserTest {

    public void testParser(String userInput, Parser parser){
        Parser parserFromInput = new Parser(userInput);

        System.out.println(parserFromInput);
        assert parserFromInput.toString().equals(parser.toString());
    }

    @Test
    public void test1(){
        String userInput = "command argument /amount amount /paid paid /user user1 /user user2";
        HashMap<String, ArrayList<String>> params = new HashMap<>();

        params.put("amount", new ArrayList<>(List.of("amount")));
        params.put("paid", new ArrayList<>(List.of("paid")));
        params.put("user", new ArrayList<>(Arrays.asList("user1", "user2")));
        Parser parser = new Parser(userInput,"command", "argument", params);

        System.out.println(parser);

        testParser(userInput, parser);
    }
}
