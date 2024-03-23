package seedu.duke;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Ui.printGreeting();
        boolean userSaysBye = false;
        FileSave file = new FileSave("omni.txt");
        TravelActivityList list = new TravelActivityList();
        file.readFile(list);
        String line;
        Scanner in = new Scanner(System.in);
        while (!userSaysBye) {
            try {
                line = in.nextLine();
                assert line != null :"Input does not exist!";
                String[] command = line.split(" ");
                switch (command[0].toLowerCase()) {

                case "list":
                    Ui.printLine();
                    Parser.getList(command, list);
                    Ui.printLine();
                    break;

                case "add":
                    Ui.printLine();
                    Parser.addCommand(line, list);
                    Ui.printLine();
                    break;

                case "accommodation":
                    Ui.printLine();
                    Parser.activityCommand(line, list);
                    Ui.printLine();
                    break;

                case "food":
                    Ui.printLine();
                    Parser.activityCommand(line, list);
                    Ui.printLine();
                    break;

                case "landmark":
                    Ui.printLine();
                    Parser.activityCommand(line, list);
                    Ui.printLine();
                    break;

                case "delete":
                    Ui.printLine();
                    Parser.deleteCommand(command, list);
                    Ui.printLine();
                    break;

                case "check":
                    Ui.printLine();
                    Parser.checkCommand(command, list);
                    Ui.printLine();
                    break;

                case "uncheck":
                    Ui.printLine();
                    Parser.uncheckCommand(command, list);
                    Ui.printLine();
                    break;

                case "find":
                    Ui.printLine();
                    Parser.findCommand(command, list);
                    Ui.printLine();
                    break;

                case "tag":
                    Ui.printLine();
                    Parser.tagCommand(line, list);
                    Ui.printLine();
                    break;

                case "untag":
                    Ui.printLine();
                    Parser.removeTagCommand(command, list);
                    Ui.printLine();
                    break;

                case "help":
                    Ui.printLine();
                    Ui.helpCommand();
                    Ui.printLine();
                    break;

                case "bye":
                    Ui.printBye();
                    userSaysBye = true;
                    break;

                case "update":
                    Ui.printLine();
                    Parser.updateCommand(line, list);
                    Ui.printLine();
                    break;

                default:
                    Ui.printLine();
                    System.out.println("This is not a valid command");
                    Ui.printLine();
                }
                file.saveActivityList(list);
            } catch (OmniException exception){
                Ui.printException(exception);
            } catch (NoSuchElementException exception){
                Ui.printNoSuchElementException(exception);
            } catch (NumberFormatException exception) {
                Ui.printNumberTooLargeException(exception);
            } catch (DateTimeException exception){
                Ui.printDateTimeExceptionError();
            } catch (IOException exception){
                Ui.printSavingError();
            }
        }
    }



}



