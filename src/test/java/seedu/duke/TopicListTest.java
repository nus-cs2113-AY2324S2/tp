package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TopicListTest {
    Topic topicTest1;
    Topic topicTest2;
    QuestionsList qnList;
    QuestionsList qnList2;
    TopicList topicListTest;


    void createTopicList(){
        topicTest1 = new Topic(qnList, "topicTest1", false, "covers topic 1" );
        topicTest2 = new Topic(qnList2, "topicTest1", false, "covers topic 2" );

        topicListTest = new TopicList();

        topicListTest.addTopic(topicTest1);
        topicListTest.addTopic(topicTest2);
    }

    @Test
    void testGetTopic(){
        createTopicList();
        assertEquals("topicTest1",topicListTest.getTopic(1));
    }

    @Test
    void testGetSize(){
        createTopicList();
        assertEquals(2, topicListTest.getSize());
    }
}
