package io.zygma.wallet;

import io.zygma.crypto.ECDSA;
import io.zygma.crypto.HashUtils;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class Wallet {
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private final String address;

    public Wallet() {
        KeyPair keyPair = ECDSA.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
        this.address = generateAddress();
    }

    private String generateAddress() {
        String pubEncoded = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        return HashUtils.sha256(pubEncoded).substring(0, 40); // vou deixar com endereços curtos, de até 40 hex chars
    }

    public String getAddress() { return address; }
    public PrivateKey getPrivateKey() { return privateKey; }
    public PublicKey getPublicKey() { return publicKey; }

    public byte[] sign(String message) {
        return ECDSA.sign(privateKey, message.getBytes());
    }

    public static boolean verify(PublicKey publicKey, String message, byte[] signature) {
        return ECDSA.verify(publicKey, message.getBytes(), signature);
    }

    @Override
    public String toString() {
        return "Wallet{" + "address='" + address + "'}";
    }
}
