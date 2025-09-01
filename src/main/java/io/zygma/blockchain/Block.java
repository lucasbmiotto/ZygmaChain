package io.zygma.blockchain;

import io.zygma.crypto.HashUtils;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Block {
    private final long timestamp;  
    private final String previousHash; 
    private final String data; 
    private final int nonce;

    private final List<Transaction> transactions; 
    private final String hash; 

    public Block(String previousHash, String data) {
        this(previousHash, data, List.of(), Instant.now().toEpochMilli(), 0);
    }

    public Block(String previousHash, List<Transaction> transactions) {
        this(previousHash, "", transactions, Instant.now().toEpochMilli(), 0);
    }

    Block(String previousHash, String data, long timestamp, int nonce) {
        this(previousHash, data, List.of(), timestamp, nonce);
    }

    Block(String previousHash, String data, List<Transaction> transactions, long timestamp, int nonce) {
        this.previousHash = Objects.requireNonNull(previousHash, "previousHash");
        this.data = data == null ? "" : data;
        this.transactions = transactions == null ? List.of() : List.copyOf(transactions);
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.hash = computeHash();
    }

    private String computeHash() {
        String txRoot = computeTxRoot();
        String content = previousHash + "|" + timestamp + "|" + nonce + "|" + data + "|" + txRoot;
        return HashUtils.sha256(content);
    }

    private String computeTxRoot() {
        if (transactions.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Transaction tx : transactions) {
            sb.append(HashUtils.sha256(tx.getData()));
        }
        return HashUtils.sha256(sb.toString());
    }

    public String getHash() { return hash; }
    public String getPreviousHash() { return previousHash; }
    public long getTimestamp() { return timestamp; }
    public int getNonce() { return nonce; }
    public String getData() { return data; }
    public List<Transaction> getTransactions() { return Collections.unmodifiableList(transactions); }

    @Override
    public String toString() {
        return "Block{" +
                "timestamp=" + timestamp +
                ", previousHash='" + previousHash + '\'' +
                ", nonce=" + nonce +
                ", hash='" + hash + '\'' +
                ", txCount=" + transactions.size() +
                ", data='" + (data.length() > 64 ? data.substring(0,64) + "…" : data) + '\'' +
                '}';
    }
}
