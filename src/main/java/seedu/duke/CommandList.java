package seedu.duke;

import seedu.duke.ui.Ui;
import seedu.duke.ai.Ai;

public enum CommandList {
    BYE, SHOOT
//    LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    //insert new user command name here
    ;

    /**
     * Exits the program
     */
    public static void executeBye() {
        Formatter.printGoodbyeMsg();
        Ui.setIsRunning(false);
    }
    public static boolean goalCheck(int userInput, int AiInput) {
        return userInput != AiInput;
    }
    public static void executeShoot(Parser userCommandReader){
        //        void viewgoal function;
        //        int userShoot function;
        //        int AI_goalkeeper;
        //        void viewgoal (int shoot, boolean goalCheck)
//        System.out.println("Function");

        String selectedDirection = userCommandReader.getArgumentTokens()[0];
        int selectedDirectionIndex =  Integer.parseInt(selectedDirection);
        boolean isScoreGoal = goalCheck(Ai.getAiDirection(), selectedDirectionIndex);

        Formatter.printGoalAfterShot(isScoreGoal);
    }

//    /**
//     * Prints user list
//     */
//    public static void executeList() {
//        if (Ui.tasks.isEmpty()) {
//            Formatter.printListEmpty();
//        } else {
//            Formatter.printListAll();
//        }
//    }

//    /**
//     * Returns the index location of task in list and marks the task in list
//     *
//     * @param userCommandReader the parsed user input
//     * @return index location of task in list
//     */
//    public static int executeMark(Parser userCommandReader) {
//        int userSelectedIndex = Integer.parseInt(userCommandReader.getArgumentTokens()[0]);
//        if (userSelectedIndex <= Ui.tasks.size() && userSelectedIndex > 0) {
//            Ui.tasks.get(userSelectedIndex - 1).markAsDone();
//            Formatter.printMarkDoneNotif(userSelectedIndex - 1);
//        } else {
//            Formatter.printErrorIndexOutOfRange();
//        }
//        return userSelectedIndex;
//    }
//
//    /**
//     * Returns the index location of task in list and unmarks the task in list
//     *
//     * @param userCommandReader the parsed user input
//     * @return index location of task in list
//     */
//    public static int executeUnmark(Parser userCommandReader) {
//        int userSelectedIndex = Integer.parseInt(userCommandReader.getArgumentTokens()[0]);
//        if (userSelectedIndex <= Ui.tasks.size() && userSelectedIndex > 0) {
//            Ui.tasks.get(userSelectedIndex - 1).markAsNotDone();
//            Formatter.printMarkUndoneNotif(userSelectedIndex - 1);
//        } else {
//            Formatter.printErrorIndexOutOfRange();
//        }
//        return userSelectedIndex;
//    }
//
//    /**
//     * Returns and adds a Todo task into list
//     *
//     * @param userCommandReader the parsed user input
//     * @return The todo task
//     */
//    public static Todo executeTodo(Parser userCommandReader) {
//        Todo newTodo = new Todo(userCommandReader.getArgumentTokens()[0]);
//        Ui.tasks.add(newTodo);
//        Formatter.printTaskNotif(newTodo);
//        return newTodo;
//    }
//
//    /**
//     * Returns and adds a Deadline task into list
//     *
//     * @param userCommandReader the parsed user input
//     * @return The deadline task
//     */
//    public static Deadline executeDeadline(Parser userCommandReader) {
//        Deadline newDeadline = new Deadline(userCommandReader.getArgumentTokens()[0], userCommandReader.getArgumentTokens()[1]);
//        Ui.tasks.add(newDeadline);
//        Formatter.printTaskNotif(newDeadline);
//        return newDeadline;
//    }
//
//    /**
//     * Returns and adds a Event task into list
//     *
//     * @param userCommandReader the parsed user input
//     * @return The event task
//     */
//    public static Event executeEvent(Parser userCommandReader) {
//        String description = userCommandReader.getArgumentTokens()[0];
//        String startTime = userCommandReader.getArgumentTokens()[1];
//        String endTime = userCommandReader.getArgumentTokens()[2];
//
//        Event newEvent = new Event(description, startTime, endTime);
//        Ui.tasks.add(newEvent);
//        Formatter.printTaskNotif(newEvent);
//        return newEvent;
//    }
//
//    /**
//     * Returns the index of deleted task and deletes task from list
//     *
//     * @param userCommandReader the parsed user input
//     * @return The index of deleted task
//     */
//    public static int executeDelete(Parser userCommandReader) {
//        int userSelectedIndex = Integer.parseInt(userCommandReader.getArgumentTokens()[0]);
//        if (userSelectedIndex <= Ui.tasks.size() && userSelectedIndex > 0) {
//            Task removedTask = Ui.tasks.remove(userSelectedIndex - 1);
//            Formatter.printDeleteNotif(removedTask);
//        } else {
//            Formatter.printErrorIndexOutOfRange();
//        }
//        return userSelectedIndex;
//    }
//
//    public static void executeFind(Parser userCommandReader) {
//        String userQuery = userCommandReader.getArgumentTokens()[0];
//        Formatter.printMatches(userQuery);
//    }
    //insert new command here
}

