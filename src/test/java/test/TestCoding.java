package test;

import com.cryptotool.util.HexUtils;
import com.cryptotool.util.MyBase64;
import org.junit.Test;

public class TestCoding {
    @Test
    public void testBase64(){
        byte[] data = "aaaaa".getBytes();
        //编码
        byte[] base64 = MyBase64.encodeToArray(data);
        byte[] dData = MyBase64.decodeFromArray(base64);
        System.out.println(new String(dData));
    }

    @Test
    public void testHex(){
        byte[] data = "aaaaaaa".getBytes();
        String hex = HexUtils.binaryToHexString(data);
        byte[] dData = HexUtils.hexStringToBinary(hex);
        System.out.println(new String(dData));

    }
}
