package brokeculator.storage.parsing;

import java.util.Map;

public class FileKeyword {

    private static final Map<SaveableType, String> FILE_KEYWORDS = Map.of(
        SaveableType.EXPENSE, "--expense--"
    );

    public static String formatWithKeyword(SaveableType saveableType, String originalStringRepresentation) {
        return FILE_KEYWORDS.get(saveableType) + originalStringRepresentation;
    }

    public static SaveableType getSaveableType(String fileString) {
        for (Map.Entry<SaveableType, String> entry : FILE_KEYWORDS.entrySet()) {
            String keyword = entry.getValue();
            SaveableType saveableType = entry.getKey();
            if (fileString.startsWith(keyword)) {
                return saveableType;
            }
        }
        return null;
    }

    public static String removeKeyword(String fileString) {
        SaveableType saveableType = getSaveableType(fileString);
        if (saveableType == null) {
            return fileString;
        }
        return fileString.replace(FILE_KEYWORDS.get(saveableType), "");
    }
}
