package test;

import com.cryptotool.block.DIG;
import com.cryptotool.block.Padding;
import com.cryptotool.block.Pattern;
import com.cryptotool.block.SE;
import com.cryptotool.cipher.MyCipher;
import com.cryptotool.cipher.MyCipherFactory;
import com.cryptotool.digests.DigestFactory;
import com.cryptotool.digests.MyDigest;
import com.cryptotool.util.KeyUtils;
import com.cryptotool.util.MyStringUtils;
import org.junit.Test;

public class TestEfficency {
    private  long n = 10000000;
    @Test
    public void test1() {
        byte[] key = KeyUtils.getSecretKey(SE.SM4);
        MyCipher cipher = MyCipherFactory.getSECipher(SE.SM4, Pattern.CBC, Padding.PKCS5, key);
        String data = "hello word";
        byte[] en_data = cipher.encrypt(data.getBytes());
        long start = System.currentTimeMillis();
        for (long i =0;i<n;i++){
            cipher.decrypt(en_data);
        }
        long end = System.currentTimeMillis();
        System.out.println((end-start)+"ms");
        System.out.println((end-start)/(n+0.0)+"ms");
    }
    @Test
    public void testSM3() throws Exception {
        String str = "hello word";
        MyDigest digest = DigestFactory.getDigest(DIG.SM3);
        long start = System.currentTimeMillis();
        for (long i =0;i<n;i++){
            digest.getDigest(str);
        }
        long end = System.currentTimeMillis();
        System.out.println((end-start)+"ms");
        System.out.println((end-start)/(n+0.0)+"ms");
    }

    @Test
    public void testR(){
        byte[] randomByte = MyStringUtils.getRandomByte(64);
        System.out.println(randomByte.length);
    }
}
