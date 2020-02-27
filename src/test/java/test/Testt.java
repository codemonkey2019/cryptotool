package test;


import com.liu.cryptotool.block.SigAlgorithm;
import org.junit.Test;

import java.io.FileNotFoundException;

public class Testt {
    @Test
    public void test1() throws FileNotFoundException {

        System.out.println(SigAlgorithm.NONEWITHRSA.toString().indexOf("RSA"));
    }

}
