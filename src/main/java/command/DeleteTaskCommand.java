package command;

import activeedge.task.WaterTask;
import activeedge.ui.CommandUi;
import activeedge.task.Task;
import activeedge.task.TaskList;

public class DeleteTaskCommand {
    private String description;

    public DeleteTaskCommand(String inputTrimmed) {
        String[] parts = inputTrimmed.split(" ", 2); // Split at the first space
        if (parts.length == 2) {
            this.description = parts[1].trim();
        } else {
            CommandUi.printInvalidDeleteFormatMessage();
        }
    }

    public void execute() {
        // Search for the task with the specified description
        boolean taskFound = false;
        for (int i = 0; i < TaskList.tasksList.size(); i++) {
            Task task = TaskList.tasksList.get(i);
            if (task.getDescription().toLowerCase().startsWith("water")) {
                if (task instanceof WaterTask) { // Check if it's a WaterTask before casting
                    WaterTask waterTask = (WaterTask) task;
                    if ((waterTask.getQuantity() + " ml").equalsIgnoreCase(description)) {
                        Task deletedTask = TaskList.delete(i);
                        CommandUi.printTaskDeletedMessage(deletedTask);
                        taskFound = true;
                        break;
                    }
                }
            } else if (task.getDescription().equalsIgnoreCase(description)) {
                Task deletedTask = TaskList.delete(i);
                CommandUi.printTaskDeletedMessage(deletedTask);
                taskFound = true;
                break;
            }
        }
        if (!taskFound) {
            CommandUi.printTaskNotFoundMessage();
        }
    }
}
