package io.zygma.wallet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {
    // Report do primeiro teste: BUILD SUCCESSFUL in 15s 3 actionable tasks: 3 executed
    @Test
    void walletShouldGenerateAddressAndSign() {
        Wallet w = new Wallet();
        assertNotNull(w.getAddress());
        byte[] sig = w.sign("data");
        assertTrue(Wallet.verify(w.getPublicKey(), "data", sig));
    }
}