package newsonthego;

import java.util.Comparator;

public class TopicComparator implements Comparator<NewsTopic> {
    @Override
    public int compare(NewsTopic t1, NewsTopic t2) {
        return t1.getTopicName().compareTo(t2.getTopicName());
    }
}
