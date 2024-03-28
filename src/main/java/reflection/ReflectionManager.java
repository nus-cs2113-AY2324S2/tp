package reflection;

import exceptions.ReflectException;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manages reflection-related operations.
 */
public class ReflectionManager {
    private static final String[] HELP_MENU_INSTRUCTIONS = {
        "reflect get: Get 5 random reflection questions",
        "reflect save <reflection_id>: Save reflection question by id to favourites list",
        "reflect unsave <reflection_id>: Unsave reflection question by id from favourites list",
        "reflect list: Retrieve questions from favourites list",
        "reflect help: Get help menu for reflect commands"
    };
    private static final String FAVOURITE_QUESTIONS_FILE_PATH = "data/favourites.txt";
    private ArrayList<ReflectionQuestion> fiveRandomQuestions;
    private ReflectionQuestionBank questionBank;
    private FavoriteReflectionsList favoriteReflectionsList;

    /**
     * Constructs a ReflectionManager and initializes question bank and favorite reflections list.
     */
    public ReflectionManager() {
        this.questionBank = new ReflectionQuestionBank();

        this.favoriteReflectionsList = new FavoriteReflectionsList();
        ArrayList<String> favouritesList = Storage.loadDataFromFile(FAVOURITE_QUESTIONS_FILE_PATH);

        for (String fav : favouritesList) {
            ReflectionQuestion reflectionQuestion = new ReflectionQuestion(fav);
            this.favoriteReflectionsList.addReflectionQuestion(reflectionQuestion);
        }
    }

    /**
     * Prints five random reflection questions.
     */
    public void printFiveRandomQuestions() {
        try {
            fiveRandomQuestions = questionBank.getFiveRandomQuestions();
            Ui.printList(fiveRandomQuestions, "Generated Questions:");
        } catch (ReflectException e) {
            Ui.printMessageWithSepNewLine(e.getMessage());
        }
    }

    /**
     * Saves a reflection question to favorites.
     *
     * @param reflectionId The ID of the reflection question to save.
     * @throws ReflectException if an error occurs during saving.
     */
    public void saveReflectionQuestion(int reflectionId) throws ReflectException {
        try {
            ReflectionQuestion questionToSave = fiveRandomQuestions.get(reflectionId - 1);

            favoriteReflectionsList.addReflectionQuestion(questionToSave);
            Storage.saveTasksToFile(FAVOURITE_QUESTIONS_FILE_PATH, favoriteReflectionsList.getReflectionList());

            Ui.printMessageWithSepNewLine("Got it. Added reflection question to favourites:\n" + questionToSave);

        } catch (IndexOutOfBoundsException e) {
            throw new ReflectException("Key in valid favourite reflection ID, between 1 and 5");

        } catch (NullPointerException e) {
            throw new ReflectException("No questions generated yet. Generate questions using 'reflect get' " +
                    "command first.");
        }
    }

    /**
     * Unsaves a reflection question from favorites.
     *
     * @param reflectionId The ID of the reflection question to be unsaved.
     * @throws ReflectException if an error occurs during saving.
     */
    public void unsaveReflectionQuestion(int reflectionId) throws ReflectException {
        try {
            ReflectionQuestion questionToUnsave = favoriteReflectionsList.get(reflectionId - 1);

            favoriteReflectionsList.removeReflectionQuestion(questionToUnsave);
            Storage.saveTasksToFile(FAVOURITE_QUESTIONS_FILE_PATH, favoriteReflectionsList.getReflectionList());

            Ui.printMessageWithSepNewLine("Got it. Unsaved reflection question from favourites:\n" + questionToUnsave);

        } catch (IndexOutOfBoundsException e) {
            throw new ReflectException("Key in valid favourite reflection ID, key in 'reflect list' command to " +
                    "view range of questions in your favourites list. ");
        }
    }

    /**
     * Prints the list of favorite reflection questions.
     */
    public void printFavourites() {
        if(favoriteReflectionsList.getReflectionList().isEmpty()) {
            Ui.printMessageWithSepNewLine("No reflection questions saved to favourites");
        } else {
            Ui.printList(favoriteReflectionsList.getReflectionList(), "Favourites list:");
        }
    }

    /**
     * Prints the help menu for reflection commands.
     */
    public void printHelpMenu() {
        ArrayList<String> helpMenuInstructionsList = new ArrayList<>(Arrays.asList(HELP_MENU_INSTRUCTIONS));

        assert helpMenuInstructionsList.size() == 5 : "Help menu should have 5 instructions";

        Ui.printList(helpMenuInstructionsList, "Commands for reflection feature:");
    }

}
