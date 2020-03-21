package test;


import com.cryptotool.block.AE;
import com.cryptotool.block.Padding;
import com.cryptotool.block.Pattern;
import com.cryptotool.block.SE;
import com.cryptotool.cipher.MyCipher;
import com.cryptotool.cipher.MyCipherFactory;
import com.cryptotool.util.HexUtils;
import com.cryptotool.util.KeyUtils;
import com.cryptotool.cipher.asymmetric.AEKeyPair;
import org.junit.Assert;
import org.junit.Test;

public class TestCipher {
    @Test
    public void test0x(){
        byte[] key = KeyUtils.getSecretKey(SE.SM4);
        MyCipher cipher = MyCipherFactory.getSECipher(SE.SM4, Pattern.CBC, Padding.PKCS5,key);
        String data = "hello word";
        byte[] en_data = cipher.encryptFile(data.getBytes());

        String hex = HexUtils.binaryToHexString(en_data);

        byte[] de_data = cipher.decryptFile(HexUtils.hexStringToBinary(hex));

        Assert.assertFalse(!"hello word".equals(new String(de_data)));
    }

    @Test
    public void testSM4() throws Exception {
        byte[] key = KeyUtils.getSecretKey(SE.SM4);
        MyCipher cipher = MyCipherFactory.getSECipher(SE.SM4, Pattern.CBC, Padding.PKCS5,key);
        String data = "hello word";
        byte[] en_data = cipher.encryptFile(data.getBytes());
        byte[] de_data = cipher.decryptFile(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));
    }

//    @Test
//    public void testAESFile() throws Exception {
//        byte[] key = KeyUtils.getSecretKey(SE.AES);
//        MyCipher cipher = MyCipherFactory.getSECipher(SE.AES, Pattern.CBC, Padding.PKCS5,key);
//        String inPath = "src/test/resources/OXT.xlsx";
//        String outPath = "src/test/resources/11.txt";
//        cipher.encrypt(inPath,outPath);
//        cipher.decrypt(outPath,"src/test/resources/2.xlsx");
//    }

    @Test
    public void testAES() throws Exception {
        byte[] key = KeyUtils.getSecretKey(SE.AES);
        MyCipher cipher = MyCipherFactory.getSECipher(SE.AES, Pattern.CBC, Padding.PKCS5,key);
        String data = "hello word";
        byte[] en_data = cipher.encryptFile(data.getBytes());
        byte[] de_data = cipher.decryptFile(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));
    }
    @Test
    public void testDES() throws Exception {
        byte[] key = KeyUtils.getSecretKey(SE.DES);
        MyCipher cipher = MyCipherFactory.getSECipher(SE.DES, Pattern.CBC, Padding.PKCS5,key);
        String data = "hello word";
        byte[] en_data = cipher.encryptFile(data.getBytes());
        byte[] de_data = cipher.decryptFile(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));
    }

    @Test
    public void testRSA(){
        AEKeyPair key = KeyUtils.getAEKeyPair(AE.RSA);
        MyCipher cipher = MyCipherFactory.getAECipher(AE.RSA,key.getPrivateKey(),key.getPublicKey());
        String data = "hello word";
        byte[] en_data = cipher.encryptFile(data.getBytes());
        byte[] de_data = cipher.decryptFile(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));

    }

    @Test
    public void testELGAMAL(){
        AEKeyPair key = KeyUtils.getAEKeyPair(AE.ELGAMAL);
        MyCipher cipher = MyCipherFactory.getAECipher(AE.ELGAMAL,key.getPrivateKey(),key.getPublicKey());
        String data = "hello word";
        byte[] en_data = cipher.encryptFile(data.getBytes());
        byte[] de_data = cipher.decryptFile(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));

    }
    @Test
    public void testSM2(){
        AEKeyPair key = KeyUtils.getAEKeyPair(AE.SM2);
        MyCipher cipher = MyCipherFactory.getAECipher(AE.SM2,key.getPrivateKey(),key.getPublicKey());
        String data = "hello word";
        byte[] en_data = cipher.encryptFile(data.getBytes());
        byte[] de_data = cipher.decryptFile(en_data);
        Assert.assertFalse(!"hello word".equals(new String(de_data)));

    }
}