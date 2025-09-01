package io.zygma.blockchain;
import io.zygma.wallet.Wallet;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BlockTxTest {
    @Test
    void miningBlockWithTransactionsShouldWorkAndChangeHash() {
        Wallet a = new Wallet();
        Wallet b = new Wallet();

        Transaction t1 = new Transaction(a.getAddress(), b.getAddress(), 5.0);
        t1.signTransaction(a.getPrivateKey());
        Transaction t2 = new Transaction(a.getAddress(), b.getAddress(), 1.5);
        t2.signTransaction(a.getPrivateKey());

        Blockchain bc = new Blockchain(2);
        bc.addBlock(List.of(t1, t2));

        assertEquals(2, bc.getChain().size());
        assertTrue(bc.isValid());
        assertEquals(2, bc.getChain().get(1).getTransactions().size());
    }
}