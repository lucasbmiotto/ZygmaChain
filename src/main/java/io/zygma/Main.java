package io.zygma;

import io.zygma.blockchain.Block;

// Teste pra ver se ta rodando mesmo, visualmente

/*  REPORT DO PRIMEIRO TESTE:
 * 
 *  > Task :run
 *  Bloco criado:
 *  Block{timestamp=1755884797036, previousHash='0', nonce=0, hash='9e85a813bce44d222f6fcb929dfe8c11253b34f99eb9cbb3f57a2a794262068a', data='Primeiro bloco da ZygmaChain'}
 */

public class Main {
    public static void main(String[] args) {
        Block genesis = new Block("0", "Primeiro bloco da ZygmaChain");
        System.out.println("Bloco criado:");
        System.out.println(genesis);
    }
}
