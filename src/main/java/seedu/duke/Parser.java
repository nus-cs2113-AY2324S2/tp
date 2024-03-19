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
            case "": // by deafult, it will be "gen"
            case "gen":
            case "generate": //@晨珖 姚 <1014717807@QQ.com>
                //ProblemGenerator ;
                ProblemGenerator pb = new ProblemGenerator();
                pb.TypeChoose();
                break;
            case "help": 
                helpCommand(description, ui);
                break;
            case "exit":
                ui.exit();
                break;
            default:
                ui.invalidCommand();
                break;
        }
    }

    public static void helpCommand(String command, Ui ui) {
        // Help: Input instructions
        // if null, print general help
        // if (command == "") {
        //     ui.help("");
        //     return;
        // }

        ui.help(command);
    
    }

}