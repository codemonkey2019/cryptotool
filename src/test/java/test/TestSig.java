package test;


import com.liu.cryptotool.block.SigAlgorithm;
import com.liu.cryptotool.control.OperateKey;
import com.liu.cryptotool.control.SignatureFactory;
import com.liu.cryptotool.signature.MySignature;
import com.liu.cryptotool.utils.AEKeyPair;
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
        AEKeyPair keyPair = OperateKey.getSigKeyPair(SigAlgorithm.SHA1WITHDSA);
        MySignature signature = SignatureFactory.getSignature(SigAlgorithm.SHA1WITHDSA, keyPair.getPrivateKey(), keyPair.getPublicKey());
        byte[] data = "hello word".getBytes();
        byte[] sign = signature.sign(data);
        Assert.assertFalse(!signature.verify(data, sign));
    }
    @Test
    public void testSigRSA(){
        AEKeyPair keyPair = OperateKey.getSigKeyPair(SigAlgorithm.SHA256WITHRSA);
        MySignature signature = SignatureFactory.getSignature(SigAlgorithm.SHA256WITHRSA, keyPair.getPrivateKey(), keyPair.getPublicKey());
        byte[] data = "hello word".getBytes();
        byte[] sign = signature.sign(data);
        Assert.assertFalse(!signature.verify(data, sign));
    }
    @Test
    public void testSigSM2(){
        AEKeyPair keyPair = OperateKey.getSigKeyPair(SigAlgorithm.NONEWITHSM2);
        MySignature signature = SignatureFactory.getSignature(SigAlgorithm.NONEWITHSM2, keyPair.getPrivateKey(), keyPair.getPublicKey());
        byte[] data = "hello word".getBytes();
        byte[] sign = signature.sign(data);
        Assert.assertFalse(!signature.verify(data, sign));
    }
}
