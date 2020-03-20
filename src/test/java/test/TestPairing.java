package test;

import com.cryptotool.util.PairingUtils;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import org.junit.Assert;
import org.junit.Test;

public class TestPairing {
    @Test
    public void testGetPairing() {
        Pairing pairing = PairingUtils.getPairingA();
        Assert.assertTrue(true);
    }

    @Test
    public void testPairingCompute() {
        Pairing pairing = PairingUtils.getPairingA();
        Field G1 = pairing.getG1();
        Field Z = pairing.getZr();
        Element g1 = G1.newRandomElement();
        Element z = Z.newRandomElement();
        Assert.assertTrue(g1.mulZn(z)!=null);
    }
}
