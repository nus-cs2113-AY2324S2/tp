package florizz.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BouquetTest {
    @Test
    void testToString() {
        Bouquet testBouquet =  new Bouquet("For Mom");
        assertEquals("For Mom", testBouquet.toString());
    }

    @Test
    void testEquals(){
        Bouquet testBouquet1 = new Bouquet ("For Mom");
        Bouquet testBouquet2 = new Bouquet ("For Mom");
        Bouquet testBouquet3 = new Bouquet ("For GF");
        assertEquals(testBouquet1, testBouquet2);
        assertNotEquals(testBouquet1, testBouquet3);
    }
}
