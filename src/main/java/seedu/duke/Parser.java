package seedu.duke;

public class Parser {

    public static void parse(String command, Ui ui) {

        /*
         * Example of commands:
         *
         * Generate problem sets:
         * gen -t 1 -n 2 -d 3
         * 
         * Help function:
         * help
         */

        // Split the command into two parts: action and description
        String[] parts = command.split(" ", 2);
        String action = parts[0];
        String description = "";
        if (parts.length > 1) {
            description = parts[1];
        }

        switch (action) {
            // notice: write your parser function by your own
            case "": // by default, it will be "gen"
            case "gen":
            case "generate":
                //ProblemGenerator ;
                ProblemGenerator pb = new ProblemGenerator();
                Test test = pb.typeChoose();
                Checker checker = new Checker(test);
                checker.getUserAnswer();
                System.out.println("Acc: "+checker.getAccuracy());
                System.out.println("Spend Time: "+checker.getTime()+"s");
                break;
            case "help": 
                ui.help(command);
                break;
            case "exit":
                ui.exit();
                break;
            default:
                ui.invalidCommand();
                break;
        }
    }
}
