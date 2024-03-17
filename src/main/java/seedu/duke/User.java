package seedu.duke;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<TaskList> taskLists; //THIS COULD BE TIMETABLE
    private Timetable timetable = new Timetable();
    public User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public User() {
        taskLists = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            taskLists.add(new TaskList());
        }
    }

    public void addUserTask(int dayOfWeek, Task task){
        if(dayOfWeek >= 0 && dayOfWeek < 7){
            taskLists.get(dayOfWeek).addTask(task);
        }
    }
    public void deleteUserTask(int dayOfWeek, int index){
        if (dayOfWeek >= 0 && dayOfWeek < 7) {
            TaskList tasks = taskLists.get(dayOfWeek);
            if (index >= 0 && index < tasks.size()){
                Task taskDeleted = tasks.get(index);
                tasks.deleteTask(index);
                System.out.println("Task " + taskDeleted.description + "is deleted from " + dayOfWeek);

            } else{
                System.out.println("Invalid task index. Please try again");
            }
        } else{
            System.out.println("Invalid day of the week.");
        }
    }
}
