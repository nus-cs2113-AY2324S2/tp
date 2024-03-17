package seedu.duke;

import java.util.ArrayList;

public class Timetable {
    //todo
    private ArrayList<Task>[] daysOfWeek;
    public Timetable() {
        daysOfWeek = new ArrayList[7];
        initializeTimetable();
    }

    private void initializeTimetable() {
        for (int i = 0; i < 7; i++) {
            daysOfWeek[i] = new ArrayList<>();
        }
    }
    protected String name;
    public void addUserTask(int dayOfWeek, Task task){
        if(dayOfWeek >= 0 && dayOfWeek < 7){
            daysOfWeek[dayOfWeek - 1].add(task);
        }
    }

    public void deleteUserTask(int dayOfWeek, int index){
        if (dayOfWeek >= 0 && dayOfWeek < 7) {

            ArrayList<Task> tasks = daysOfWeek[dayOfWeek - 1];
            if (index >= 0 && index < tasks.size()){
                Task taskDeleted = tasks.get(index);
                tasks.remove(index);
                System.out.println("Task " + taskDeleted.description + "is deleted from " + dayOfWeek);

            } else{
                System.out.println("Invalid task index. Please try again");
            }
        } else{
            System.out.println("Invalid day of the week.");
        }
    }
}
