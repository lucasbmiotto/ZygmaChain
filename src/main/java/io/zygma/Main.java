package io.zygma;
import io.zygma.blockchain.Blockchain;
import io.zygma.blockchain.Transaction;
import io.zygma.wallet.Wallet;
import io.zygma.blockchain.Block;
import java.util.List;

// Teste pra ver se ta rodando mesmo, visualmente

/*  REPORT DO TESTE DE MÚLTIPLAS TRANSAÇÕES NA BLOCKCHAIN:
 *
 *  > Task :run
 *
 *  --- Testando Blockchain com múltiplas transações ---
 *  Blockchain criada:
 *
 *  Block{timestamp=1756745261465, previousHash='0', nonce=0, hash='af2c0b66a581269d727b3ef981991ac9666bde928a157ce08294b2d23b9a976d', txCount=0, data='Genesis Block'}
 *  Block{timestamp=1756745261483, previousHash='af2c0b66a581269d727b3ef981991ac9666bde928a157ce08294b2d23b9a976d', nonce=214, hash='00871f3768f971056e7b260fa70d0e8a6c364450a26a20b614f038bc4b0646b9', txCount=2, data=''}
 *    Transaction{from='9c0f5db3309b1b48965fb30c1f6a6807cbc48ea2', to='80c531176b3894e089e15b96b475bd1817405efd', amount=5.0, signature=MEYCIQC6BYRE/Nxo…}
 *    Transaction{from='9c0f5db3309b1b48965fb30c1f6a6807cbc48ea2', to='80c531176b3894e089e15b96b475bd1817405efd', amount=1.5, signature=MEUCIQCqo4hK5bOC…}
 *
 *  Blockchain válida? true
 *
 */

/*  REPORT DO TESTE DE ASSINATURA DE TRANSAÇÃO:
 *
 *  > Task :run
 *
 *  --- Testando assinatura de transação ---
 *  Transação criada: Transaction{from='61d44ca91ed6a8b969e803f08a27099896605269', to='2fa00a08dd75b34f55f9da1e5ed23bbebb3ff934', amount=50.0, signature=<unsigned>}
 *  Transação assinada: Transaction{from='61d44ca91ed6a8b969e803f08a27099896605269', to='2fa00a08dd75b34f55f9da1e5ed23bbebb3ff934', amount=50.0, signature=MEQCICehTPyfjZ2K…}
 *  Assinatura válida? true
 *  Assinatura válida para transação adulterada? false
 *
 *  BUILD SUCCESSFUL
 */

public class Main {
    public static void main(String[] args) {
        testBlockchainComTransacoes();
        testTransacaoAssinatura();
    }

    private static void testBlockchainComTransacoes() {
        System.out.println("\n--- Testando Blockchain com múltiplas transações ---");
        Wallet a = new Wallet();
        Wallet b = new Wallet();

        Transaction t1 = new Transaction(a.getAddress(), b.getAddress(), 5.0);
        t1.signTransaction(a.getPrivateKey());
        Transaction t2 = new Transaction(a.getAddress(), b.getAddress(), 1.5);
        t2.signTransaction(a.getPrivateKey());

        Blockchain bc = new Blockchain(2);
        bc.addBlock(List.of(t1, t2));

        System.out.println("Blockchain criada:");
        for (Block block : bc.getChain()) {
            System.out.println(block);
            for (Transaction tx : block.getTransactions()) {
                System.out.println("  " + tx);
            }
        }
        System.out.println("Blockchain válida? " + bc.isValid());
    }

    private static void testTransacaoAssinatura() {
        System.out.println("\n--- Testando assinatura de transação ---");
        Wallet sender = new Wallet();
        Wallet receiver = new Wallet();

        Transaction tx = new Transaction(sender.getAddress(), receiver.getAddress(), 50.0);
        System.out.println("Transação criada: " + tx);

        tx.signTransaction(sender.getPrivateKey());
        System.out.println("Transação assinada: " + tx);

        boolean assinaturaValida = tx.isSignatureValid(sender.getPublicKey());
        System.out.println("Assinatura válida? " + assinaturaValida);

        Transaction txAdulterada = new Transaction(sender.getAddress(), receiver.getAddress(), 999.0);
        txAdulterada.setSignature(tx.getSignature());
        boolean assinaturaInvalida = txAdulterada.isSignatureValid(sender.getPublicKey());
        System.out.println("Assinatura válida para transação adulterada? " + assinaturaInvalida);
    }
}