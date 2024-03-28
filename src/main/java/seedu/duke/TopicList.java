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

    public String[][] listAllTopics() {
        int commandNum = topicList.size();
        String[][] tableData = new String[commandNum][];
        for (int i = 0; i < commandNum; i++) {
            tableData[i] = new String[]{
                    String.valueOf(i + 1),
                    topicList.get(i).topicName,
                    topicList.get(i).summary,
                    String.valueOf(topicList.get(i).hasAttempted())
            };
        }
        return tableData;
    }

    public int getSize() {
        return topicList.size();
    }

    public Topic get(int index){
        return topicList.get(index);
    }

}
