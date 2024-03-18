package reflection;

import exceptions.ReflectException;

import java.util.Collections;
import java.util.ArrayList;

public class ReflectionQuestionBank {
    private ArrayList<ReflectionQuestion> reflectionQuestionList;
    private final String[] questions = {
        "What have been the most significant lessons you've learned about yourself in the past year?",
        "How have your values evolved over time, and why?",
        "What habits or behaviors do you want to cultivate or change to become a better version of yourself?",
        "Reflect on a recent failure or setback. What did you learn from it, and how will you apply those lessons " +
                "moving forward?",
        "In what areas do you feel you've made the most progress recently, and what contributed to that progress?",
        "What are your biggest strengths, and how can you leverage them more effectively in your daily life?",
        "Describe a moment when you stepped outside of your comfort zone. What did you discover about yourself?",
        "How do you define success, and how has that definition evolved over time?",
        "What activities or hobbies bring you the most joy and fulfillment, and how can you incorporate more of them " +
                "into your life?",
        "Reflect on a time when you received constructive criticism. How did you react, and what did you learn from " +
                "the experience?",
        "Who are the people in your life who bring you the most happiness and support? How do you nurture " +
                "those relationships?",
        "Have there been any conflicts or misunderstandings in your relationships recently? How have you worked " +
                "to resolve them?",
        "Reflect on a time when you felt truly understood by someone. What made that connection so meaningful?",
        "How do you prioritize spending time with loved ones amidst your busy schedule?",
        "What qualities do you admire most in your closest friends or family members, and why?",
        "Are there any relationships in your life that feel strained or unfulfilling? How can you address " +
                "those challenges?",
        "Reflect on your communication style. In what ways do you excel, and where do you see room for improvement?",
        "Describe a memorable shared experience with someone you care about. What made it special?",
        "How do you express gratitude toward the people who enrich your life?",
        "Reflect on a time when you forgave someone or were forgiven. What did you learn from that experience?",
        "What motivates you to excel in your career, beyond financial rewards?",
        "Reflect on a recent professional success. What factors contributed to your achievement?",
        "In what ways do you seek to grow and develop within your current role or industry?",
        "How do you balance pursuing your career ambitions with maintaining a healthy work-life balance?",
        "Reflect on a time when you faced a significant challenge at work. How did you overcome it?",
        "What steps are you taking to advance your skills and knowledge in your field?",
        "Describe a mentor or role model who has had a profound impact on your career journey. " +
                "What lessons have you learned from them?",
        "How do you navigate workplace conflicts or disagreements with colleagues or superiors?",
        "Reflect on your career goals. Are they still aligned with your passions and values, or have they evolved?",
        "What do you consider to be your greatest professional achievement so far, and why?",
        "How do you prioritize self-care and well-being in your daily life?",
        "Reflect on a recent creative project or activity that brought you joy. What inspired you to pursue it?",
        "In what ways do you express yourself creatively, whether through art, writing, music, or other mediums?",
        "Describe a time when you felt in a state of flow, completely absorbed in a creative endeavor.",
        "How do you overcome creative blocks or periods of stagnation?",
        "Reflect on a piece of art, literature, or music that has deeply resonated with you. " +
                "What emotions or insights did it evoke?",
        "What role does creativity play in your life, and how do you nurture it?",
        "How do you make time for creative pursuits amidst your other responsibilities and commitments?",
        "Reflect on a time when you took a creative risk. What did you learn from the experience?",
        "Describe a recent moment when you felt inspired by something or someone in your environment.",
        "How do you celebrate your unique talents and creative voice?"
    };

    public ReflectionQuestionBank() {
        this.reflectionQuestionList = new ArrayList<>();
        setUpReflectionBank();
    }

    private void setUpReflectionBank() {
        for(String question : questions) {
            ReflectionQuestion reflectionQuestion = new ReflectionQuestion(question);
            addReflectionQuestion(reflectionQuestion);
        }
    }

    public void addReflectionQuestion(ReflectionQuestion question) {
        if (!question.toString().isBlank()) {
            reflectionQuestionList.add(question);
        }
    }

    public ArrayList<ReflectionQuestion> getFiveRandomQuestions() throws ReflectException {
        try {
            ArrayList<ReflectionQuestion> randomQuestions = new ArrayList<>();

            // Create a copy of the original list
            ArrayList<ReflectionQuestion> copyList = new ArrayList<>(reflectionQuestionList);

            // Shuffle the copy list
            Collections.shuffle(copyList);

            // Select the first five questions from the shuffled copy list and
            // add them to the result list
            for (int i = 0; i < 5; i++) {
                randomQuestions.add(copyList.get(i));
            }

            return randomQuestions;
        } catch (IndexOutOfBoundsException e) {
            throw new ReflectException("Question bank is empty");
        }

    }

    public int getTaskListSize() {
        return reflectionQuestionList.size();
    }

    public ArrayList<ReflectionQuestion> getQuestionsList() {
        return reflectionQuestionList;
    }
}
