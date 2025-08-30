package io.zygma;
import io.zygma.blockchain.Blockchain;
import io.zygma.wallet.Wallet;

// Teste pra ver se ta rodando mesmo, visualmente

/*  REPORT DO PRIMEIRO BLOCO CRIADO TESTE:
 * 
 *  > Task :run
 *  Bloco criado:
 *  Block{timestamp=1755884797036, previousHash='0', nonce=0, hash='9e85a813bce44d222f6fcb929dfe8c11253b34f99eb9cbb3f57a2a794262068a', data='Primeiro bloco da ZygmaChain'}
 */

 /*  REPORT DA BLOCKCHAIN COMPLETA TESTE:
 *
 *  > Task :run
 *  blocos adicionados na ZygmaChain...
 *
 *  Blockchain criada:
 *
 *  Block{timestamp=1756315078981, previousHash='0', nonce=0, hash='979f0e66d31a4081c47cc9b622f5a345f945544b5cff31a98117bc867d6a3fbf', data='Genesis Block'}
 *  Block{timestamp=1756315079033, previousHash='979f0e66d31a4081c47cc9b622f5a345f945544b5cff31a98117bc867d6a3fbf', nonce=18, hash='00cbe8547c0e64bf012753c3d96893ab7af993901a7b1b10cce77837ffe1d408', data='tx: X paga 10 ZYG para U'}
 *  Block{timestamp=1756315079052, previousHash='00cbe8547c0e64bf012753c3d96893ab7af993901a7b1b10cce77837ffe1d408', nonce=105, hash='003d57cf31f9fd9c18c9c4797e650b527350836924aff2f26804e1d2757be611', data='tx: U paga 5 ZYG para Y'}
 *  Block{timestamp=1756315079078, previousHash='003d57cf31f9fd9c18c9c4797e650b527350836924aff2f26804e1d2757be611', nonce=279, hash='00d0512667c41c569946174f52a56aa4f35544ebd1b122ac8e76a8230f778939', data='tx: Y paga 2 ZYG para A'}
 *
 *  Blockchain válida? true
 */

 /*  REPORT DO TESTE DA WALLET E ECDSA:
 *
 *  > Task :run
 *  --- Testando Wallet e ECDSA ---
 *  Endereço da wallet: 1744aeb313208c17804b6c9deefbaa66fbc0162f
 *  Assinatura válida? true
 *  Assinatura válida para mensagem alterada? false
 *
 */

public class Main {
    public static void main(String[] args) {

        Blockchain blockchain = new Blockchain(2);
        System.out.println("blocos adicionados na ZygmaChain...\n");
        blockchain.addBlock("tx: X paga 10 ZYG para U");
        blockchain.addBlock("tx: U paga 5 ZYG para Y");
        blockchain.addBlock("tx: Y paga 2 ZYG para A");
        System.out.println("Blockchain criada:\n");
        System.out.println(blockchain);
        System.out.println("Blockchain é válida? " + blockchain.isValid());

        // Teste da Wallet e ECDSA
        System.out.println("\n--- Testando Wallet e ECDSA ---");
        Wallet wallet = new Wallet();
        System.out.println("Endereço da wallet: " + wallet.getAddress());

        String mensagem = "Teste de assinatura";
        byte[] assinatura = wallet.sign(mensagem);
        boolean valido = Wallet.verify(wallet.getPublicKey(), mensagem, assinatura);

        System.out.println("Assinatura válida? " + valido);

        // Testando verificação com mensagem alterada
        boolean invalido = Wallet.verify(wallet.getPublicKey(), "mensagem alterada", assinatura);
        System.out.println("Assinatura válida para mensagem alterada? " + invalido);
    }
}
