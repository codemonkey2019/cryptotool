package test;


import com.liu.cryptotool.block.AE;
import com.liu.cryptotool.block.Padding;
import com.liu.cryptotool.block.Pattern;
import com.liu.cryptotool.block.SE;
import com.liu.cryptotool.cipher.MyCipher;
import com.liu.cryptotool.cipher.CipherFactory;
import com.liu.cryptotool.utils.OperateKey;
import com.liu.cryptotool.utils.AEKeyPair;
import org.junit.Assert;
import org.junit.Test;

public class TestCipher {

    @Test
    public void testSM4() throws Exception {
        byte[] key = OperateKey.getSecretKey(SE.SM4);
        MyCipher cipher = CipherFactory.getSECipher(SE.SM4, Pattern.CBC, Padding.PKCS5,key);
        String data = "hello word";
        byte[] en_data = cipher.encrypt(data.getBytes());
        byte[] de_data = cipher.decrypt(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));
    }
    @Test
    public void testAES() throws Exception {
        byte[] key = OperateKey.getSecretKey(SE.AES);
        MyCipher cipher = CipherFactory.getSECipher(SE.AES, Pattern.CBC, Padding.PKCS5,key);
        String data = "hello word";
        byte[] en_data = cipher.encrypt(data.getBytes());
        byte[] de_data = cipher.decrypt(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));
    }
    @Test
    public void testDES() throws Exception {
        byte[] key = OperateKey.getSecretKey(SE.DES);
        MyCipher cipher = CipherFactory.getSECipher(SE.DES, Pattern.CBC, Padding.PKCS5,key);
        String data = "hello word";
        byte[] en_data = cipher.encrypt(data.getBytes());
        byte[] de_data = cipher.decrypt(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));
    }

    @Test
    public void testRSA(){
        AEKeyPair key = OperateKey.getAEKeyPair(AE.RSA);
        MyCipher cipher = CipherFactory.getAECipher(AE.RSA,key.getPrivateKey(),key.getPublicKey());
        String data = "hello word";
        byte[] en_data = cipher.encrypt(data.getBytes());
        byte[] de_data = cipher.decrypt(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));

    }
    @Test
    public void testELGAMAL(){
        AEKeyPair key = OperateKey.getAEKeyPair(AE.ELGAMAL);
        MyCipher cipher = CipherFactory.getAECipher(AE.ELGAMAL,key.getPrivateKey(),key.getPublicKey());
        String data = "hello word";
        byte[] en_data = cipher.encrypt(data.getBytes());
        byte[] de_data = cipher.decrypt(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));

    }
    @Test
    public void testSM2(){
        AEKeyPair key = OperateKey.getAEKeyPair(AE.SM2);
        MyCipher cipher = CipherFactory.getAECipher(AE.SM2,key.getPrivateKey(),key.getPublicKey());
        String data = "hello word";
        byte[] en_data = cipher.encrypt(data.getBytes());
        byte[] de_data = cipher.decrypt(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));

    }
}