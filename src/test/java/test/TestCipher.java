package test;


import com.liu.cryptotool.block.AEAlgorithm;
import com.liu.cryptotool.block.Padding;
import com.liu.cryptotool.block.Pattern;
import com.liu.cryptotool.block.SEAlgorithm;
import com.liu.cryptotool.control.MyCipher;
import com.liu.cryptotool.control.CipherFactory;
import com.liu.cryptotool.control.OperateKey;
import com.liu.cryptotool.utils.AEKeyPair;
import org.junit.Assert;
import org.junit.Test;

public class TestCipher {

    @Test
    public void testSM4() throws Exception {
        byte[] key = OperateKey.getSecretKey(SEAlgorithm.SM4);
        MyCipher cipher = CipherFactory.getSECipher(SEAlgorithm.SM4, Pattern.CBC, Padding.PKCS5,key);
        String data = "hello word";
        byte[] en_data = cipher.encrypt(data.getBytes());
        byte[] de_data = cipher.decrypt(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));
    }
    @Test
    public void testAES() throws Exception {
        byte[] key = OperateKey.getSecretKey(SEAlgorithm.AES);
        MyCipher cipher = CipherFactory.getSECipher(SEAlgorithm.AES, Pattern.CBC, Padding.PKCS5,key);
        String data = "hello word";
        byte[] en_data = cipher.encrypt(data.getBytes());
        byte[] de_data = cipher.decrypt(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));
    }
    @Test
    public void testDES() throws Exception {
        byte[] key = OperateKey.getSecretKey(SEAlgorithm.DES);
        MyCipher cipher = CipherFactory.getSECipher(SEAlgorithm.DES, Pattern.CBC, Padding.PKCS5,key);
        String data = "hello word";
        byte[] en_data = cipher.encrypt(data.getBytes());
        byte[] de_data = cipher.decrypt(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));
    }

    @Test
    public void testRSA(){
        AEKeyPair key = OperateKey.getKeyPair(AEAlgorithm.RSA);
        MyCipher cipher = CipherFactory.getAECipher(AEAlgorithm.RSA,key.getPrivateKey(),key.getPublicKey());
        String data = "hello word";
        byte[] en_data = cipher.encrypt(data.getBytes());
        byte[] de_data = cipher.decrypt(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));

    }
    @Test
    public void testELGAMAL(){
        AEKeyPair key = OperateKey.getKeyPair(AEAlgorithm.ELGAMAL);
        MyCipher cipher = CipherFactory.getAECipher(AEAlgorithm.ELGAMAL,key.getPrivateKey(),key.getPublicKey());
        String data = "hello word";
        byte[] en_data = cipher.encrypt(data.getBytes());
        byte[] de_data = cipher.decrypt(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));

    }
    @Test
    public void testSM2(){
        AEKeyPair key = OperateKey.getKeyPair(AEAlgorithm.SM2);
        MyCipher cipher = CipherFactory.getAECipher(AEAlgorithm.SM2,key.getPrivateKey(),key.getPublicKey());
        String data = "hello word";
        byte[] en_data = cipher.encrypt(data.getBytes());
        byte[] de_data = cipher.decrypt(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));

    }
}