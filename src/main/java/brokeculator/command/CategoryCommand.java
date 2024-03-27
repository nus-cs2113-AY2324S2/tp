package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.enumerators.Category;
import brokeculator.frontend.UI;

import java.util.ArrayList;

public class CategoryCommand extends Command {
    public static final String ADD_SUBCOMMAND = "add";
    public static final String DELETE_SUBCOMMAND = "delete";
    public static final String LIST_SUBCOMMAND = "list";
    String subcommand;
    String value;
    public CategoryCommand(String subcommand) {
        this.subcommand = subcommand;
    };
    public CategoryCommand(String subcommand, String value) {
        this.subcommand = subcommand;
        this.value = value;
    };
    @Override
    public void execute(Dashboard dashboard) {
        switch (subcommand) {
        case ADD_SUBCOMMAND:
            addCategory(value);
            break;
        case DELETE_SUBCOMMAND:
            deleteCategory(value);
            break;
        case LIST_SUBCOMMAND:
            listCategories();
            break;
        default:
            break;
        }
    }
    private void addCategory(String value) {
        String feedback = Category.addCategory(value);
        UI.prettyPrint(feedback);
    }
    private void deleteCategory(String value) {
        String feedback = Category.removeCategory(value);
        UI.prettyPrint(feedback);
    }
    private void listCategories() {
        ArrayList<String> categoryList = Category.getCategoryList();
        if (categoryList.isEmpty()) {
            UI.prettyPrint("No categories found");
            return;
        }
        String categoryListSting = Category.getCategoryListString();
        UI.prettyPrint("Categories:" + System.lineSeparator() + categoryListSting);
    }
}
