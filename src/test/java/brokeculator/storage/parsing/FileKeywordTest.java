package brokeculator.storage.parsing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

class FileKeywordTest {
    @Test
    void formatWithKeyword_stringStatus_containsOriginalStringRepresentation() {
        String originalStringRepresentation = "expense_details";
        String formattedString = FileKeyword.formatWithKeyword(SaveableType.EXPENSE, originalStringRepresentation);
        assertTrue(formattedString.contains(originalStringRepresentation));
    }

    @Test 
    void getSaveableType_saveableTypeRecorded_correctSaveableType() {
        String originalStringRepresentation = "expense_details";
        String formattedString = FileKeyword.formatWithKeyword(SaveableType.EXPENSE, originalStringRepresentation);
        SaveableType saveableType = FileKeyword.getSaveableType(formattedString);
        assertEquals(SaveableType.EXPENSE, saveableType);
    }

    @Test 
    void getSaveableType_noSaveableTypeRecorded_null() {
        String fileString= "random_corrupted_file_string";
        SaveableType saveableType = FileKeyword.getSaveableType(fileString);
        assertNull(saveableType);
    }

    @Test 
    void removeKeyword_keywordRemoved_originalStringRepresentation() {
        String originalStringRepresentation = "expense_details";
        String formattedString = FileKeyword.formatWithKeyword(SaveableType.EXPENSE, originalStringRepresentation);
        String stringRepresentationWithoutKeyword = FileKeyword.removeKeyword(formattedString);
        assertEquals(originalStringRepresentation, stringRepresentationWithoutKeyword);
    }
}
