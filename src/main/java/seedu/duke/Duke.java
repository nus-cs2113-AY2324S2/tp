package seedu.duke;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        printGreeting();
        boolean userSaysBye = false;
        TravelActivityList travelActivityList = new TravelActivityList();
        String line;
        Scanner in = new Scanner(System.in);
        while (!userSaysBye) {
            line = in.nextLine();
            if (line.equals("list")) {
                // Prints the all the tasks in the list
                System.out.println("Here are the travel activities in your list:");
                travelActivityList.listTasks();
            } else if (line.startsWith("add")){
                // Adds a travel activity into the list
                String[] sentence = line.split("add");
                // Checks if the description of the task is empty
                TravelActivity newTask = new TravelActivity(sentence[1]);
                travelActivityList.addTask(newTask);
                System.out.println("I added a new travel activity");
                System.out.println(newTask);

            } else if(line.startsWith("delete")){
                // Deletes the task in the list
                String[] sentence = line.split(" ");
                // Checks if the description of the task is empty or non-numerical

                int taskNumber = Integer.parseInt(sentence[1]);
                travelActivityList.removeTask(taskNumber);
            } else if(line.startsWith("bye")){
                userSaysBye = true;
            }

        }
    }

    public static void printGreeting() {
        System.out.println("Hello");
        System.out.println("How may I assist you?");

    }
}



