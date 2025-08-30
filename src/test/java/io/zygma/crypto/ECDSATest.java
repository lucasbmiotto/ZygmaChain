package io.zygma.crypto;

import org.junit.jupiter.api.Test;
import java.security.KeyPair;
import static org.junit.jupiter.api.Assertions.*;

public class ECDSATest {
    // Report do primeiro teste: BUILD SUCCESSFUL in 15s 3 actionable tasks: 3 executed
    @Test
    void signAndVerifyShouldWork() {
        KeyPair pair = ECDSA.generateKeyPair();
        byte[] sig = ECDSA.sign(pair.getPrivate(), "hello".getBytes());
        assertTrue(ECDSA.verify(pair.getPublic(), "hello".getBytes(), sig));
        assertFalse(ECDSA.verify(pair.getPublic(), "bye".getBytes(), sig));
    }
}