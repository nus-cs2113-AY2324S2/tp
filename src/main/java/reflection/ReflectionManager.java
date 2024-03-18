package reflection;

import exceptions.ReflectException;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

public class ReflectionManager {
    private ArrayList<ReflectionQuestion> fiveRandomQuestions;
    private ReflectionQuestionBank questionBank;
    private FavoriteReflectionsList favoriteReflectionsList;
    private final String FAVOURITE_QUESTIONS_FILE_PATH = "data/favourites.txt";
    public ReflectionManager() {
        this.questionBank = new ReflectionQuestionBank();

        this.favoriteReflectionsList = new FavoriteReflectionsList();
        ArrayList<String> favouritesList = Storage.loadDataFromFile(FAVOURITE_QUESTIONS_FILE_PATH);
        for (String fav : favouritesList) {
            ReflectionQuestion reflectionQuestion = new ReflectionQuestion(fav);
            this.favoriteReflectionsList.addReflectionQuestion(reflectionQuestion);
        }
    }

    public void printFiveRandomQuestions() {
        try {
            fiveRandomQuestions = questionBank.getFiveRandomQuestions();
            Ui.printList(fiveRandomQuestions);
        } catch (ReflectException e) {
            Ui.printMessageWithSepNewLine(e.getMessage());
        }
    }

    public void saveReflectionQuestion(int reflectionId) throws ReflectException {
        try {
            ReflectionQuestion questionToSave = fiveRandomQuestions.get(reflectionId - 1);

            favoriteReflectionsList.addReflectionQuestion(questionToSave);
            Storage.saveTasksToFile(FAVOURITE_QUESTIONS_FILE_PATH, favoriteReflectionsList.getFavouritesList());

            Ui.printMessageWithSepNewLine("Got it. Added reflection question to favourites:\n" + questionToSave);

        } catch (IndexOutOfBoundsException e) {
            throw new ReflectException("Key in valid favourite reflection ID, between 1 and 5");

        } catch (NullPointerException e) {
            throw new ReflectException("No questions generated yet. Generate questions using 'reflect get' " +
                    "command first.");
        }
    }

    public ReflectionQuestionBank getQuestionBank() {
        return questionBank;
    }

}
