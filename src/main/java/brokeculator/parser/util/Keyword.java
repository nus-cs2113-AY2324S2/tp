package brokeculator.parser.util;

public class Keyword {
    public final String keywordMarker; 
    public final String keywordMeaning;
    public final boolean isOptional;

    public Keyword(String keywordMarker, String keywordMeaning, boolean isOptional) {
        this.keywordMarker = keywordMarker;
        this.keywordMeaning = keywordMeaning;
        this.isOptional = isOptional;
    }
}
