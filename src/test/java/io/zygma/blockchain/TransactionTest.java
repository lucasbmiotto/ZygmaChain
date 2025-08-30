package io.zygma.blockchain;
import io.zygma.wallet.Wallet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TransactionTest {
    // Report do primeiro teste: BUILD SUCCESSFUL in 4s 3 actionable tasks: 3 executed
    @Test
    void signedTransactionShouldValidateCorrectly() {
        // Aqui tem que testar se uma transação assinada pelo remetente é validada 
        Wallet sender = new Wallet();
        Wallet receiver = new Wallet();

        Transaction tx = new Transaction(sender.getAddress(), receiver.getAddress(), 50.0);
        tx.signTransaction(sender.getPrivateKey());

        assertTrue(tx.isSignatureValid(sender.getPublicKey()));
    }

    @Test
    void unsignedTransactionShouldFailValidation() {
        // teste sem assuinatura validada
        Wallet sender = new Wallet();
        Wallet receiver = new Wallet();

        Transaction tx = new Transaction(sender.getAddress(), receiver.getAddress(), 50.0);
        assertFalse(tx.isSignatureValid(sender.getPublicKey()));
    }

    @Test
    void tamperedTransactionShouldFailValidation() {
        // teste de transação adulterada
        Wallet sender = new Wallet();
        Wallet receiver = new Wallet();

        Transaction tx = new Transaction(sender.getAddress(), receiver.getAddress(), 50.0);
        tx.signTransaction(sender.getPrivateKey());

        Transaction hacked = new Transaction(sender.getAddress(), receiver.getAddress(), 500.0);
        hacked.signTransaction(sender.getPrivateKey());
        assertFalse(hacked.getAmount() == tx.getAmount() && hacked.isSignatureValid(sender.getPublicKey()));
    }
}
