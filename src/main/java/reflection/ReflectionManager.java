package reflection;

import exceptions.ReflectException;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

/**
 * Manages reflection-related operations.
 */
public class ReflectionManager {
    private ArrayList<ReflectionQuestion> fiveRandomQuestions;
    private ReflectionQuestionBank questionBank;
    private FavoriteReflectionsList favoriteReflectionsList;
    private String favouriteQuestionsFilePath = "data/favourites.txt";
    private static final String BOT_NAME = "Wellness360";

    /**
     * Constructs a ReflectionManager and initializes question bank and favorite reflections list.
     */
    public ReflectionManager() {
        this.questionBank = new ReflectionQuestionBank();

        this.favoriteReflectionsList = new FavoriteReflectionsList();
        ArrayList<String> favouritesList = Storage.loadDataFromFile(favouriteQuestionsFilePath);
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
            Storage.saveTasksToFile(favouriteQuestionsFilePath, favoriteReflectionsList.getFavouritesList());

            Ui.printMessageWithSepNewLine("Got it. Added reflection question to favourites:\n" + questionToSave);

        } catch (IndexOutOfBoundsException e) {
            throw new ReflectException("Key in valid favourite reflection ID, between 1 and 5");

        } catch (NullPointerException e) {
            throw new ReflectException("No questions generated yet. Generate questions using 'reflect get' " +
                    "command first.");
        }
    }

    /**
     * Prints the list of favorite reflection questions.
     */
    public void printFavourites() {
        if(favoriteReflectionsList.getFavouritesList().isEmpty()) {
            Ui.printMessageWithSepNewLine("No reflection questions saved to favourites");
        } else {
            Ui.printList(favoriteReflectionsList.getFavouritesList(), "Favourites list:");
        }
    }

    /**
     * Retrieves the question bank.
     *
     * @return The ReflectionQuestionBank instance.
     */
    public ReflectionQuestionBank getQuestionBank() {
        return questionBank;
    }

}
