package test;

import com.cryptotool.block.AE;
import com.cryptotool.block.SE;
import com.cryptotool.util.KeyUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestKeyUtils {
    @Test
    public void testRandomGenKey(){
        KeyUtils.getSecretKey(SE.SM4);
        KeyUtils.getSecretKey(SE.AES);
        KeyUtils.getSecretKey(SE.DES);

        KeyUtils.getAEKeyPair(AE.SM2);
        KeyUtils.getAEKeyPair(AE.RSA);
        KeyUtils.getAEKeyPair(AE.ELGAMAL);
        Assert.assertTrue(true);
    }
    @Test
    public void testRandomGenKeyBySeed(){
        byte[] seed = "seed".getBytes();
        KeyUtils.getAEKeyPairBySeed(AE.ELGAMAL,seed);
        KeyUtils.getAEKeyPairBySeed(AE.RSA,seed);
        KeyUtils.getAEKeyPairBySeed(AE.RSA,seed);

        KeyUtils.getSecretKeyBySeed(SE.SM4,seed);
        KeyUtils.getSecretKeyBySeed(SE.DES,seed);
        KeyUtils.getSecretKeyBySeed(SE.AES,seed);
        Assert.assertTrue(true);
    }
}
