package florizz.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlowerTest {

    @Test
    void testGetName() {
        Flower flower = new Flower("Orchid", Flower.Colour.WHITE, Flower.Occasion.WEDDING, "$10.00");
        assertEquals("Orchid",flower.getFlowerName());
    }

}
