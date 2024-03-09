public enum CommandType {
    EXIT("(?i)bye"),
    HELP("(?i)help"),
    CREATE_ORDER("(?i)create\\s*order\\s*-menu\\s*(\\d+)"), // case insensitive space safe
    VIEW_ORDER("(?i)view\\s*-order\\s*(\\d+)"),
    EDIT_ORDER("(?i)edit\\s*-order\\s*(\\d+)");

    private final String commandRegex;
    CommandType(String commandRegex) {
        this.commandRegex = commandRegex;
    }

    String getCommandRegex() {
        return commandRegex;
    }
}
