package io.zygma.blockchain;

import io.zygma.crypto.HashUtils;
import java.time.Instant;
import java.util.Objects;

// Bloco básico da blockchain, contendo:
public class Block {
private final long timestamp;
private final String previousHash;
private final String data;
private final int nonce;

private final String hash; // hash do bloco (SHA-256)

public Block(String previousHash, String data) {
    this(previousHash, data, Instant.now().toEpochMilli(), 0);
}

Block(String previousHash, String data, long timestamp, int nonce) {
    this.previousHash = Objects.requireNonNull(previousHash, "previousHash");
    this.data = data == null ? "" : data;
    this.timestamp = timestamp;
    this.nonce = nonce;
    this.hash = computeHash();
}

private String computeHash() {
    String content = previousHash + "|" + timestamp + "|" + nonce + "|" + data;
    return HashUtils.sha256(content);
}

public String getHash() { return hash; }
public String getPreviousHash() { return previousHash; }
public long getTimestamp() { return timestamp; }
public int getNonce() { return nonce; }
public String getData() { return data; }

@Override
public String toString() {
return "Block{" +
"timestamp=" + timestamp +
", previousHash='" + previousHash + '\'' +
", nonce=" + nonce +
", hash='" + hash + '\'' +
", data='" + (data.length() > 64 ? data.substring(0,64) + "…" : data) + '\'' +
'}';
}
}