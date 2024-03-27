package seedu.budgetbuddy;

public enum CommandPrefix {
    FIND(new String[]{"d/", "morethan/", "lessthan/"}),
    REC(new String[]{"to/", "c/", "a/", "d/"}),
    ADD(new String[]{"c/", "a/", "d/"});

    private final String[] nextPrefixes;

    CommandPrefix(String[] nextPrefixes) {
        this.nextPrefixes = nextPrefixes;
    }

    public String[] getNextPrefixes() {
        return nextPrefixes;
    }

}
