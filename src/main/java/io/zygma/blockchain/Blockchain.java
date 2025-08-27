package io.zygma.blockchain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Blockchain {
    private final List<Block> chain;
    private final int difficulty;

    public Blockchain(int difficulty) {
        if (difficulty < 1) throw new IllegalArgumentException("Dificuldade deve ser >= 1");
        this.difficulty = difficulty;
        this.chain = new ArrayList<>();
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block("0", "Genesis Block");
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(String data) {
        Block previous = getLatestBlock();
        Block newBlock = mineBlock(previous.getHash(), data);
        chain.add(newBlock);
    }

    private Block mineBlock(String previousHash, String data) {
        long timestamp = System.currentTimeMillis();
        int nonce = 0;
        String targetPrefix = String.join("", Collections.nCopies(difficulty, "0"));

        Block candidate;
        do {
            candidate = new Block(previousHash, data, timestamp, nonce++);
        } while (!candidate.getHash().startsWith(targetPrefix));

        return candidate;
    }

    public boolean isValid() {
        String targetPrefix = String.join("", Collections.nCopies(difficulty, "0"));

        for (int i = 1; i < chain.size(); i++) {
            Block current = chain.get(i);
            Block previous = chain.get(i - 1);

            if (!current.getHash().startsWith(targetPrefix)) return false;
            if (!current.getPreviousHash().equals(previous.getHash())) return false;
            Block recalculated = new Block(current.getPreviousHash(), current.getData(), current.getTimestamp(), current.getNonce());
            if (!current.getHash().equals(recalculated.getHash())) return false;
        }
        return true;
    }

    public List<Block> getChain() {
        return Collections.unmodifiableList(chain);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        chain.forEach(b -> sb.append(b).append("\n"));
        return sb.toString();
    }
}