package seedu.duke;
import java.util.Random;
import java.util.ArrayList;
public class HangMan {
    protected static String[] wordBank = new String[11];
    protected static ArrayList<String> guessedLetters = new ArrayList<>();
    protected static int numberOfLettersGuessed = 0;

    public HangMan() {
        String words = "ant baboon badger bat bear beaver camel cat clam cobra cougar";
        wordBank = words.split(" ",11);
    }

    public static int getNumberOfLettersGuessed() {
        return numberOfLettersGuessed;
    }

    public static ArrayList<String> getGuessedLetters() {
        return guessedLetters;
    }

    /**
     * Takes a random word from the word bank and prints the appropriate
     * number of lines for each letter.
     */
    public static void printWordGuesser() {
        Random rand = new Random();
        int whichWord = rand.nextInt(11);
        int wordLength = wordBank[whichWord].length();

        for (int i = 0; i < wordLength; i++) {
            System.out.print(" _");
        }
        System.out.println();
    }

    /**
     * Prints the HangMan image to represent the number of chances left to guess
     */
    public static void printHangMan() {
        System.out.println("  _______");
        System.out.println("  |     |");
        System.out.println("  |     @");
        System.out.println("  |    /|\\");
        System.out.println("  |     |");
        System.out.println("  |    / \\");
        System.out.println("==============");
    }

    public static void printLettersGuessed() {
        int numOfLetters = getNumberOfLettersGuessed();
        for (int index = 0; index < numOfLetters; index++) {
            String letterToDisplay = getGuessedLetters().get(index);
            System.out.print((index+1) + ". " + letterToDisplay);
        }
        System.out.println();
    }

    public static void addGuess(String userInput) {
        if (userInput == null) {
            return; //catch exceptions?
        }
        guessedLetters.add(userInput);
        numberOfLettersGuessed += 1;
    }



}
