package minigame;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import ui.ResponseManager;

public class TypingGame implements MiniGame {
    private static final double TIME_RATIO = 1000.0;
    private static final int PERCENTAGE = 100;
    private static final int TIME_LIMIT = 20;
    private static final String TEXT_TO_TYPE = "The quick brown fox jumps over the lazy dog.";
    private static final String START_MSG = "Welcome to the Typing Game!\n"
            + "Type the following text as fast as you can:\n";
    private static final String GREEN_COLOR = "\033[0;32m";
    private static final String RED_COLOR = "\033[0;31m";
    private static final String RESET = "\033[0m";
    private static final Logger TG_LOGGER = Logger.getLogger(TypingGame.class.getName());
    private int accuracy;
    private double timeSpent;
    private final String[] userInput;

    public TypingGame() {
        this.accuracy = 0;
        this.timeSpent = 0;
        this.userInput = new String[] {""};
    }

    private static void setupLogger() {
        LogManager.getLogManager().reset();
        TG_LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        TG_LOGGER.addHandler(consoleHandler);
    }

    public void startGame() {
        setupLogger();
        CompletableFuture<String> finalScore = CompletableFuture.supplyAsync(() -> {
            Scanner scanner = new Scanner(System.in);
            ResponseManager.indentPrint(START_MSG);
            ResponseManager.indentPrint(GREEN_COLOR + TEXT_TO_TYPE + RESET + "\n");
            ResponseManager.indentPrint("Press" + RED_COLOR + "ENTER" + RESET + "to start typing..." + "\n");
            // Wait for user to press enter
            scanner.nextLine();

            typingGameLogic(scanner);
            TG_LOGGER.info("User has completed the game");
            return "Good job! You finished within the time limit!\n";
        });

        try {
            ResponseManager.indentPrint(finalScore.get(TIME_LIMIT, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            this.timeSpent = TIME_LIMIT;
            finalScore.cancel(true);
            ResponseManager.indentPrint("\nTime's up!!!! Your input is not captured TAT\n");
            TG_LOGGER.info("User did not complete the game in time");
        } catch (InterruptedException | ExecutionException e) {
            ResponseManager.indentPrint("An error occurred while calculating your score.\n");
            TG_LOGGER.log(Level.SEVERE, "An error occurred while calculating the score", e);
        }
    }

    private void typingGameLogic(Scanner scanner) {
        long startTime = System.currentTimeMillis();
        System.out.print("Type here: ");
        userInput[0] = scanner.nextLine();
        this.timeSpent = (System.currentTimeMillis() - startTime) / TIME_RATIO;
        this.accuracy = calculateAccuracy();
    }

    private int calculateAccuracy() {
        int correctCharacters = 0;
        for (int i = 0; i < Math.min(TEXT_TO_TYPE.length(), userInput[0].length()); i++) {
            if (TEXT_TO_TYPE.charAt(i) == userInput[0].charAt(i)) {
                correctCharacters++;
            }
        }
        assert correctCharacters <= TEXT_TO_TYPE.length() :
                "Correct characters should not exceed the length of the text";
        return (correctCharacters * PERCENTAGE / TEXT_TO_TYPE.length());
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public void outputResult() {
        ResponseManager.indentPrint(
                String.format("You typed at %d%% accuracy in %.2f seconds!\n", this.accuracy, this.timeSpent));
    }
}
