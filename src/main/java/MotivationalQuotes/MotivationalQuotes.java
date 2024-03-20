package MotivationalQuotes;
import java.util.Random;

public class MotivationalQuotes {

    public static final String[] MOTIVATIONALQUOTELIST = {
        "The only way to do great work is to love what you do. - Steve Jobs",
        "Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill",
        "Believe you can and you're halfway there. - Theodore Roosevelt",
        "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
        "It does not matter how slowly you go as long as you do not stop. - Confucius",
        "You are never too old to set another goal or to dream a new dream. - C.S. Lewis",
        "The only limit to our realization of tomorrow will be our doubts of today. - Franklin D. Roosevelt",
        "Success is not the key to happiness. Happiness is the key to success. " +
            "If you love what you are doing, you will be successful. - Albert Schweitzer",
        "The way to get started is to quit talking and begin doing. - Walt Disney",
        "Don't watch the clock; do what it does. Keep going. - Sam Levenson",
        "After a while you learn to ignore the " +
            "names people call you and just trust who you are. - Shrek",
        "Your limitation—it's only your imagination.",
        "Push yourself, because no one else is going to do it for you.",
        "Sometimes later becomes never. Do it now.",
        "Great things never come from comfort zones.",
        "Dream it. Wish it. Do it.",
        "Success doesn’t just find you. You have to go out and get it.",
        "The harder you work for something, the greater you’ll feel when you achieve it.",
        "Dream bigger. Do bigger.",
        "Don’t stop when you’re tired. Stop when you’re done.",
        "Wake up with determination. Go to bed with satisfaction.",
        "Do something today that your future self will thank you for.",
        "Little things make big days.",
        "It’s going to be hard, but hard does not mean impossible.",
        "Don’t wait for opportunity. Create it.",
        "Sometimes we’re tested not to show our weaknesses, but to discover our strengths.",
        "The key to success is to focus on goals, not obstacles.",
        "Dream it. Believe it. Build it.",
        "The only way to do great work is to love what you do.",
        "In the middle of difficulty lies opportunity.",
        "You are capable of more than you know.",
        "Believe in yourself and all that you are. " +
            "Know that there is something inside you that is greater than any obstacle.",
        "Don’t be pushed around by the fears in your mind. Be led by the dreams in your heart."};

    public static String getQoute() {
        Random rand = new Random();
        int index = rand.nextInt(MOTIVATIONALQUOTELIST.length);
        return MOTIVATIONALQUOTELIST[index];
    }

}
