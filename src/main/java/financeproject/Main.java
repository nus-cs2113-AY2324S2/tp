package financeproject;

import customexceptions.InactivityTimeoutException;
import command.BaseCommand;
import customexceptions.IncompletePromptException;
import financialtransactions.TransactionManager;
import parser.Parser;
import storage.Storage;
import user.InactivityTimer;
import user.Authentication;
import user.BaseUser;
import userinterface.UI;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage("./data");
        TransactionManager manager;
        manager = storage.loadFile();

        UI ui = new UI();
        ui.printMessage("Welcome. Enter your username and password to login.");

        Parser parser = new Parser();
        BaseCommand baseCommand = null;
        String response = "";

        BaseUser tempUser = new BaseUser("Bob", ui);
        Authentication auth = tempUser.getAuthentication();
        InactivityTimer inactivityTimer = new InactivityTimer();

        if (!auth.authenticate()) {
            ui.printMessage("Authentication error");
            ui.closeScanner();
            return;
        } else {
            ui.printMessage("Password is correct. You are now logged in");
            manager = new TransactionManager();
        }

        do {
            String command = ui.readInput();
            try {
                baseCommand = parser.parseCommand(command, manager);
                response = baseCommand.execute(manager);
                ui.printMessage(response);
                inactivityTimer.resetTimer();
            } catch (IncompletePromptException e) {
                if (e.getIsTypo()) {
                    System.out.println("Please prompt again with correct spelling.");
                } else if (e.getIsIncomplete()) {
                    System.out.println("Sorry, your prompt appears incomplete. Could you finish your sentence?");
                } else if (e.getIsUnknown()) {
                    System.out.println("Sorry, prompt inputted is unknown. ");
                }
            } catch (Exception e) {
                System.out.println("Uh-oh, something went wrong: " + e.getMessage());
            }

            storage.saveFile(manager);

            try {
                inactivityTimer.checkTimeElapsed();
            } catch (InactivityTimeoutException e) {
                if (e.isTimeOut()) {
                    //parser.setIsContinue(false);
                    assert baseCommand != null;
                    baseCommand.setIsExit(true);
                } else if (e.isGracePeriod()) {
                    ui.printMessage("Some time has passed. Would you still like to continue: ");
                    String wantToContinue = ui.readInput();
                    if (wantToContinue.equalsIgnoreCase("y") ||
                            wantToContinue.equalsIgnoreCase("yes")) {
                        System.out.println("Session continued.");
                        inactivityTimer.resetTimer();
                    } else if (wantToContinue.equalsIgnoreCase("n") ||
                            wantToContinue.equalsIgnoreCase("no")) {
                        //parser.setIsContinue(false);
                        System.out.println("Session ended. ");
                        assert baseCommand != null;
                        baseCommand.setIsExit(true);
                    }
                }
            }
        } while (baseCommand == null || !baseCommand.isExit());
        ui.closeScanner();
    }
}
