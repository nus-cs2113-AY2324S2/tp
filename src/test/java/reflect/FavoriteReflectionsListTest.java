package reflect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reflection.FavoriteReflectionsList;
import reflection.ReflectionQuestion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FavoriteReflectionsListTest {

    private FavoriteReflectionsList favoriteReflectionList;

    @BeforeEach
    public void setUp() {
        favoriteReflectionList = new FavoriteReflectionsList();
    }

    @Test
    public void addReflectionQuestion_addQuestion_success() {
        ReflectionQuestion question = new ReflectionQuestion("What is reflection?");
        favoriteReflectionList.addReflectionQuestion(question);
        assertEquals(1, favoriteReflectionList.getFavouritesList().size());
        assertTrue(favoriteReflectionList.getFavouritesList().contains(question));
    }

    @Test
    public void addReflectionQuestion_addBlankQuestion_skipOverBlankQuestion() {
        ReflectionQuestion question = new ReflectionQuestion("");
        favoriteReflectionList.addReflectionQuestion(question);
        assertEquals(0, favoriteReflectionList.getFavouritesList().size());
        assertFalse(favoriteReflectionList.getFavouritesList().contains(question));
    }

    @Test
    public void get_getQuestionById_success() {
        ReflectionQuestion question = new ReflectionQuestion("What is reflection?");
        favoriteReflectionList.addReflectionQuestion(question);
        ReflectionQuestion retrievedQuestion = favoriteReflectionList.get(0);
        assertEquals(question, retrievedQuestion);
    }
}

