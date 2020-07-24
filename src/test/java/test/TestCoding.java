package test;

import com.cryptotool.util.HexUtils;
import com.cryptotool.util.MyStringUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class TestCoding {
    @Test
    public void testBase64(){
        byte[] data = "aaaaa".getBytes();
        //编码
        byte[] base64 = MyStringUtils.encodeToBase64Array(data);
        byte[] dData = MyStringUtils.decodeFromBase64Array(base64);
        Assert.assertTrue(new String(dData).equals("aaaaa"));
    }

    @Test
    public void testHex() throws NoSuchPaddingException, NoSuchAlgorithmException {
        byte[] data = "aaaaaaa".getBytes();
        String hex = HexUtils.binaryToHexString(data);
        byte[] dData = HexUtils.hexStringToBinary(hex);
        Assert.assertTrue(new String(dData).equals("aaaaaaa"));

    }
}
