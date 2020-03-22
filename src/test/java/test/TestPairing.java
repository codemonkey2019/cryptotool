package test;

import com.cryptotool.util.PairingAUtils;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import org.junit.Assert;
import org.junit.Test;

public class TestPairing {
    @Test
    public void testGetPairing() {
        Pairing pairing = PairingAUtils.getPairing();
        Assert.assertTrue(true);
    }

    @Test
    public void testPairingCompute() {
        Pairing pairing = PairingAUtils.getPairing();
        Field G1 = pairing.getG1();
        Field Z = pairing.getZr();
        Element g1 = G1.newRandomElement();
        Element z = Z.newRandomElement();
        Assert.assertTrue(g1.mulZn(z)!=null);
    }

    @Test
    public void testPairiing(){
        Element g = PairingAUtils.getRandomElementOfG1();
        Element g1 = PairingAUtils.getRandomElementOfG1();
        PairingAUtils.doPairing(g,g1);
        Assert.assertTrue(true);
    }
}
