package io.zygma.crypto;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public final class ECDSA {
    private static final String EC_CURVE = "secp256r1";
    private static final String SIGN_ALGO = "SHA256withECDSA";

    private ECDSA() {}

    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec(EC_CURVE);
            keyGen.initialize(ecSpec, new SecureRandom());
            return keyGen.generateKeyPair();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Erro ao gerar par de chaves ECDSA", e);
        }
    }

    public static byte[] sign(PrivateKey privateKey, byte[] data) {
        try {
            Signature signature = Signature.getInstance(SIGN_ALGO);
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Erro ao assinar dados", e);
        }
    }

    public static boolean verify(PublicKey publicKey, byte[] data, byte[] sig) {
        try {
            Signature signature = Signature.getInstance(SIGN_ALGO);
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(sig);
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Erro ao verificar assinatura", e);
        }
    }
}
