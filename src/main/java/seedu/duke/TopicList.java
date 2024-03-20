package seedu.duke;


import java.util.ArrayList;

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

    public String getChosenTopic(int topicNum){
        int topicIndex = topicNum - 1;
        Topic topic = topicList.get(topicIndex);
        return topic.topicName;
    }

}
