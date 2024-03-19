package seedu.duke;


import java.util.ArrayList;
import java.util.Scanner;

public class TopicList {
    private ArrayList<Topic> topicList;

    public TopicList() {
        topicList = new ArrayList<>();
    }
    public void addTopic(Topic topic){
        topicList.add(topic);
    }

    public String getTopic(int index){
        return topicList.get(index).topicName;
    }

    public int getSize() {
        //System.out.println(topicList.size());
        return topicList.size();
    }

}
