package command;

public enum CommandType {
    FIGHT("(?i)\\h*(f|fight)\\h*"),
    RUN("(?i)\\h*(r|run)\\h*"),
    MOVE_FORWARD("(?i)\\h*(w)(\\h+\\d+)?\\h*"),
    MOVE_DOWNWARD("(?i)\\h*(s)(\\h+\\d+)?\\h*"),
    MOVE_LEFT("(?i)\\h*(a)(\\h+\\d+)?\\h*"),
    MOVE_RIGHT("(?i)\\h*(d)(\\h+\\d+)?\\h*"),
    INTERACT("(?i)\\h*(e)\\h*"),
    QUIT("(?i)\\h*(q|quit)\\h*"),
    HELP("(?i)\\h*(h|help)\\h*"),
    ERROR("");
    final String regExpression;

    private CommandType(String regExpression) {
        this.regExpression = regExpression;
    }

    public String getRegExpression() {
        return regExpression;
    }
}
