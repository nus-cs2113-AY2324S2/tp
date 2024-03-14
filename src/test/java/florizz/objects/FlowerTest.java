package florizz.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlowerTest {

    @Test
    void testGetName() {
        Flower flower = new Flower("Orchid","White","Marriage");
        assertEquals("Orchid",flower.getFlowerName());
    }

    @Test
    void testToString() {
        Flower flower = new Flower("Rose","Red","Valentines Day");
        assertEquals("Name: Rose\nColour: Red\nOccasion: Valentines Day\n",flower.toString());
    }
}
