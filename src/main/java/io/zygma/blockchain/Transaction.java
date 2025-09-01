package io.zygma.blockchain;

import io.zygma.crypto.ECDSA;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

public class Transaction {
    private final String senderAddress;
    private final String recipientAddress;
    private final double amount;
    private byte[] signature;

    public Transaction(String senderAddress, String recipientAddress, double amount) {
        this.senderAddress = Objects.requireNonNull(senderAddress);
        this.recipientAddress = Objects.requireNonNull(recipientAddress);
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
        this.amount = amount;
    }

    public String getData() { return senderAddress + ":" + recipientAddress + ":" + amount; }

    public void signTransaction(PrivateKey privateKey) {
        this.signature = ECDSA.sign(privateKey, getData().getBytes());
    }

    public boolean isSignatureValid(PublicKey publicKey) {
        if (signature == null) return false;
        return ECDSA.verify(publicKey, getData().getBytes(), signature);
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getSenderAddress() { return senderAddress; }
    public String getRecipientAddress() { return recipientAddress; }
    public double getAmount() { return amount; }
    public byte[] getSignature() { return signature == null ? null : Arrays.copyOf(signature, signature.length); }

    @Override
    public String toString() {
        return "Transaction{" +
                "from='" + senderAddress + '\'' +
                ", to='" + recipientAddress + '\'' +
                ", amount=" + amount +
                ", signature=" + (signature == null ? "<unsigned>" : Base64.getEncoder().encodeToString(signature).substring(0, 16) + "…") +
                '}';
    }
}