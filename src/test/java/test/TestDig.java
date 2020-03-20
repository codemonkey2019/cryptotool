package test;

import com.cryptotool.block.DIG;
import com.cryptotool.digests.DigestFactory;
import com.cryptotool.digests.MyDigest;
import org.junit.Assert;
import org.junit.Test;

public class TestDig {
    @Test
    public void testSM3() throws Exception {
        String str = "hello word";
        MyDigest digest = DigestFactory.getDigest(DIG.SM3);
        String d = digest.getDig(str);
        String d1 = digest.getDig(str);
        Assert.assertFalse(!(d1.equals(d)));
    }
    @Test
    public void testSHA256() throws Exception {
        String str = "hello word";
        MyDigest digest = DigestFactory.getDigest(DIG.SHA256);
        String d = digest.getDig(str);
        String d1 = digest.getDig(str);
        Assert.assertFalse(!(d1.equals(d)));
    }
    @Test
    public void testMD5() throws Exception {
        String str = "hello word";
        MyDigest digest = DigestFactory.getDigest(DIG.MD5);
        String d = digest.getDig(str);
        String d1 = digest.getDig(str);
        Assert.assertFalse(!(d1.equals(d)));
    }
}
