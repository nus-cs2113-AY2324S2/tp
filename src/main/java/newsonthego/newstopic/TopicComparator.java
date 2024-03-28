package newsonthego.newstopic;

import java.util.Comparator;

public class TopicComparator implements Comparator<NewsTopic> {
    @Override
    public int compare(NewsTopic topicOne, NewsTopic topicTwo) {
        return topicOne.getTopicName().compareTo(topicTwo.getTopicName());
    }
}
