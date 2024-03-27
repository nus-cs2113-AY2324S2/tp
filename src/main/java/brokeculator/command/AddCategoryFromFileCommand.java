package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.enumerators.Category;

public class AddCategoryFromFileCommand extends Command {
    private final String fileString;
    public AddCategoryFromFileCommand(String fileString) {
        this.fileString = fileString;
    }
    @Override
    public void execute(Dashboard dashboard) {
        String category = fileString;
        Category.addCategory(category);
    }
}
