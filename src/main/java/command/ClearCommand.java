/**
 * The ClearCommand class represents a command to clear all tasks and user details in the system.
 * It provides functionality to execute the command.
 */
package command;

import activeedge.task.TaskList;
import activeedge.ui.CommandUi;
import activeedge.userdetails.UserDetailsList;

/**
 * The ClearCommand class represents a command to clear all tasks and user details in the system.
 * It provides functionality to execute the command.
 */
public class ClearCommand {

    /**
     * Constructs a ClearCommand object.
     * This constructor requires no input.
     */
    public ClearCommand() {
        // No input needed for this command
    }

    /**
     * Executes the clear command.
     * Checks if both task list and user details list are empty.
     * If both lists are already empty, it prints a message indicating the data is already cleared.
     * Otherwise, it clears both lists and prints a message indicating that all tasks are cleared.
     */
    public void execute() {
        if (TaskList.tasksList.isEmpty() && UserDetailsList.DETAILS_LIST.isEmpty()) {
            CommandUi.printDataAlreadyClearedMessage();
        } else {
            TaskList.clearTasks();
            UserDetailsList.clearDetailsList();
            CommandUi.printAllTasksClearedMessage();
        }
    }
}

