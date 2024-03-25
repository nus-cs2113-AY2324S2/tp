package seedu.duke;

import java.util.ArrayList;
import java.util.Random;

public class GiftList {

    private ArrayList<Gift> gifts;

    public GiftList(ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }

    public int size() {
        return gifts.size();
    }

    public Gift get(int i) {
        return gifts.get(i);
    }

    public Gift getRandomGift() {
        Gift randomGift;
        do {
            Random random = new Random();
            int giftIndex = random.nextInt(gifts.size());
            randomGift = gifts.get(giftIndex);
        } while (randomGift.completionStatus.equals("C"));
        return randomGift;
    }
}
