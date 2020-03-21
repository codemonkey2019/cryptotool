package test;


import com.cryptotool.block.SIG;
import com.cryptotool.util.KeyUtils;
import com.cryptotool.signature.SignatureFactory;
import com.cryptotool.signature.MySignature;
import com.cryptotool.signature.SigKeyPair;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author L
 * @date 2019-10-12 14:50
 * @desc
 **/
public class TestSig {
    @Test
    public void testSigDSA(){
        SigKeyPair keyPair = KeyUtils.getSigKeyPair(SIG.SHA1WITHDSA);
        MySignature signature = SignatureFactory.getSignature(SIG.SHA1WITHDSA, keyPair.getPrivateKey(), keyPair.getPublicKey());
        byte[] data = "hello word".getBytes();
        byte[] sign = signature.sign(data);
        Assert.assertFalse(!signature.verify(data, sign));
    }
    @Test
    public void testSigRSA(){
        SigKeyPair keyPair = KeyUtils.getSigKeyPair(SIG.SHA256WITHRSA);
        MySignature signature = SignatureFactory.getSignature(SIG.SHA256WITHRSA, keyPair.getPrivateKey(), keyPair.getPublicKey());
        byte[] data = "hello word".getBytes();
        byte[] sign = signature.sign(data);
        Assert.assertFalse(!signature.verify(data, sign));
    }
    @Test
    public void testSigSM2(){
        SigKeyPair keyPair = KeyUtils.getSigKeyPair(SIG.NONEWITHSM2);
        MySignature signature = SignatureFactory.getSignature(SIG.NONEWITHSM2, keyPair.getPrivateKey(), keyPair.getPublicKey());
        byte[] data = "hello word".getBytes();
        byte[] sign = signature.sign(data);
        Assert.assertFalse(!signature.verify(data, sign));
    }
}
