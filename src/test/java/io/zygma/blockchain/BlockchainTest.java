package io.zygma.blockchain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class BlockchainTest {

    @Test
    void chainShouldStartWithGenesisBlock() {
        Blockchain bc = new Blockchain(2);
        assertEquals(1, bc.getChain().size());
        assertTrue(bc.getChain().get(0).getData().contains("Genesis"));
    }

    @Test
    void addingBlocksShouldIncreaseSizeAndKeepValid() {
        Blockchain bc = new Blockchain(2);
        bc.addBlock("tx1");
        bc.addBlock("tx2");
        assertEquals(3, bc.getChain().size());
        assertTrue(bc.isValid());
    }

    @Test
    void tamperingShouldInvalidateChain() throws Exception {
        Blockchain bc = new Blockchain(2);
        bc.addBlock("test56");

        // Clona a cadeia original e muda um bloco
        List<Block> hackedChain = new ArrayList<>(bc.getChain());
        Block original = hackedChain.get(1);
        Block forged = new Block(
            original.getPreviousHash(),
            "hacked",
            original.getTimestamp(),
            original.getNonce()
        );
        hackedChain.set(1, forged);

        // simula um ataque esterno
        java.lang.reflect.Field chainField = Blockchain.class.getDeclaredField("chain");
        chainField.setAccessible(true);
        chainField.set(bc, hackedChain);

        // aqui tem que retornar falso pro teste funcionar
        assertFalse(bc.isValid());
    }
}
