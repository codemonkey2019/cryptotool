package test;

import com.liu.cryptotool.block.DigAlgorithm;
import com.liu.cryptotool.control.DigestFactory;
import com.liu.cryptotool.digests.MyDigest;
import org.junit.Assert;
import org.junit.Test;

public class TestDig {
    @Test
    public void testSM3() throws Exception {
        String str = "hello word";
        MyDigest digest = DigestFactory.getDigest(DigAlgorithm.SM3);
        String d = digest.getHash(str);
        String d1 = digest.getHash(str);
        Assert.assertFalse(!(d1.equals(d)));
    }
    @Test
    public void testSHA256() throws Exception {
        String str = "hello word";
        MyDigest digest = DigestFactory.getDigest(DigAlgorithm.SHA256);
        String d = digest.getHash(str);
        String d1 = digest.getHash(str);
        Assert.assertFalse(!(d1.equals(d)));
    }
    @Test
    public void testMD5() throws Exception {
        String str = "hello word";
        MyDigest digest = DigestFactory.getDigest(DigAlgorithm.MD5);
        String d = digest.getHash(str);
        String d1 = digest.getHash(str);
        Assert.assertFalse(!(d1.equals(d)));
    }
}
