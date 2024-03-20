package ui;

public enum CommandType {
    // main commands
    EXIT("(?i)bye"),
    HELP("(?i)help"),
    CREATE_ORDER("(?i)create\\s*order\\s*-menu\\s*(\\d+)"), // case-insensitive, space safe

    // order commands
    VIEW_ORDER("(?i)view\\s*-order\\s*(\\d+)"),
    VIEW_ALL_ORDERS("(?i)view\\s*-order\\s*-all"),
    EDIT_ORDER("(?i)edit\\s*-order\\s*(\\d+)"),

    // edit order commands
    ADD_ITEM("(?i)add\\s*-item\\s*(\\d+)\\s*-quantity\\s*(\\d+)"),
    DELETE_ITEM("(?i)delete\\s*-item\\s*(\\d+)\\s*-quantity\\s*(\\d+)"),
    COMPLETE_ORDER("(?i)complete\\s*"),
    //COMPLETE_ORDER("(?i)complete\\s*-order\\s*(\\d+)"),

    // edit menu commands
    VIEW_MENU("(?i)view menu"),
    ADD_MENU("(?i)add\\s*-menu");

    private final String commandRegex;
    CommandType(String commandRegex) {
        this.commandRegex = commandRegex;
    }

    String getCommandRegex() {
        return commandRegex;
    }
}
