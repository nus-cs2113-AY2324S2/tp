package seedu.duke.userprofile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssetTest {
    @Test
    void outputAsset() {
        assertEquals("5000", new Asset(10000).outputAsset());
    }
}
