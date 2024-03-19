package seedu.duke;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
public class HangMan {
    protected static String[] wordBank = new String[11];
    protected static ArrayList<String> allGuessedLetters = new ArrayList<>();
    protected static int numberOfLettersGuessed = 0;
    protected static String chosenWord;
    protected static int chosenWordLength;
    protected static String correctGuesses;
    protected static int state = 0;
    protected static final String LINE = "_";

    public HangMan() {
        String words = "ant baboon badger bat bear beaver camel cat clam cobra cougar";
        wordBank = words.split(" ",11);
        Random rand = new Random();
        int whichWord = rand.nextInt(11);
        chosenWord = wordBank[whichWord];
        chosenWordLength = wordBank[whichWord].length();
        correctGuesses = LINE.repeat(chosenWordLength);
    }

    public void runHangMan() {
        String userInput;
        Scanner in = new Scanner(System.in);

        userInput = in.nextLine();

        printHangMan();
        printLettersGuessed();
        printWordGuesser();


        while (state < 6) {
            addGuess(userInput);
            printHangMan();
            printLettersGuessed();
            printWordGuesser();
            if (!correctGuesses.contains("_")) {
                System.out.println("Woahhhh you got it!!");
                break;
            }
            userInput = in.nextLine();
        }
        printHangMan();
        System.out.println();
        System.out.println("Oh noo!! It seems you have lost   :( ");
    }
    public static int getNumberOfLettersGuessed() {
        return numberOfLettersGuessed;
    }
    public static ArrayList<String> getAllGuessedLetters() {
        return allGuessedLetters;
    }
    /*
    public static String getChosenWord() {
        return chosenWord;
    }
    public static int getChosenWordLength() {
        return chosenWordLength;
    }
     */
    public static int getState() {
        return state;
    }

    /**
     * Takes the chosen word from the word bank and prints the appropriate
     * number of lines for each letter.
     */
    public static void printWordGuesser() {
        char[] charCorrectGuesses = correctGuesses.toCharArray();
        for (int i = 0; i < chosenWordLength; i++) {
            System.out.print(" " + charCorrectGuesses[i]);
        }
        System.out.println();
    }

    /**
     * Prints the HangMan image to represent the number of chances left to guess
     */
    public static void printHangMan() {
        state = getState();
        switch(state) {
        case 0:
            System.out.println("  _______");
            System.out.println("  |     |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("==============");
            break;
        case 1:
            System.out.println("  _______");
            System.out.println("  |     |");
            System.out.println("  |     @");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("==============");
            break;
        case 2:
            System.out.println("  _______");
            System.out.println("  |     |");
            System.out.println("  |     @");
            System.out.println("  |     |");
            System.out.println("  |     |");
            System.out.println("  |");
            System.out.println("==============");
            break;
        case 3:
            System.out.println("  _______");
            System.out.println("  |     |");
            System.out.println("  |     @");
            System.out.println("  |    /|");
            System.out.println("  |     |");
            System.out.println("  |");
            System.out.println("==============");
            break;
        case 4:
            System.out.println("  _______");
            System.out.println("  |     |");
            System.out.println("  |     @");
            System.out.println("  |    /|\\");
            System.out.println("  |     |");
            System.out.println("  |");
            System.out.println("==============");
            break;
        case 5:
            System.out.println("  _______");
            System.out.println("  |     |");
            System.out.println("  |     @");
            System.out.println("  |    /|\\");
            System.out.println("  |     |");
            System.out.println("  |    /");
            System.out.println("==============");
            break;
        case 6:
            System.out.println("  _______");
            System.out.println("  |     |");
            System.out.println("  |     @");
            System.out.println("  |    /|\\");
            System.out.println("  |     |");
            System.out.println("  |    / \\");
            System.out.println("==============");
            break;
        default:
            System.out.println("default");
            break;
        }

    }

    public static void printLettersGuessed() {
        System.out.println("These are the guesses you have made so far:");
        int numOfLetters = getNumberOfLettersGuessed();
        for (int index = 0; index < numOfLetters; index++) {
            String letterToDisplay = getAllGuessedLetters().get(index);
            System.out.print(letterToDisplay + "  ");
        }
        System.out.println();
    }

    public static int parseGuess(String userInput) {
        if (userInput == null) {
            return 0;
        }
        int guessType;
        if (userInput.length() == 1) {
            guessType = 1;
        } else {
            guessType = 2;
        }
        return guessType;
    }
    public static void addGuess(String userInput) {
        int guessType = parseGuess(userInput);
        if (guessType == 1) { // input is a single character
            allGuessedLetters.add(userInput);
            numberOfLettersGuessed += 1;
            if (chosenWord.contains(userInput)) { // if guess is correct
                String tempWord = chosenWord;
                char[] charCorrectGuesses = correctGuesses.toCharArray();
                int lastSearchedIndex = 0;
                while (tempWord.indexOf(userInput,lastSearchedIndex) != -1) {
                    lastSearchedIndex = tempWord.indexOf(userInput,lastSearchedIndex);
                    charCorrectGuesses[lastSearchedIndex] = userInput.charAt(0);
                }
                correctGuesses = String.valueOf(charCorrectGuesses);
            } else {
                state += 1;
            }
        } else if (guessType == 2) {
            return;
        }
    }
}
