package io.zygma.blockchain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Report do primeiro teste: BUILD SUCCESSFUL in 39s 4 actionable tasks: 4 executed
public class BlockTest {
    @Test
    void hashShouldBeDeterministicForSameInputs() {
        Block a = new Block("prev", "data", 123L, 0);
        Block b = new Block("prev", "data", 123L, 0);
        assertEquals(a.getHash(), b.getHash());
    }

    @Test
    void hashShouldChangeIfDataChanges() {
        Block a = new Block("prev", "data", 123L, 0);
        Block b = new Block("prev", "data2", 123L, 0);
        assertNotEquals(a.getHash(), b.getHash());
    }
}