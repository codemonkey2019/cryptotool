package com.cryptotool.util;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class PairingA1Utils {
    private static Pairing pairingA1 = PairingFactory.getPairing("param/a1.properties");

    public static Element getRandomElementOfG1(){
        return getG1().newRandomElement();
    }
    public static Element getRandomElementOfG2(){
        return getG2().newRandomElement();
    }

    public static Element getRandomElementOfZr(){
        return getZr().newRandomElement();
    }


    public static Field getG1() {
        return pairingA1.getG1();
    }

    public static Field getG2() {
        return pairingA1.getG2();
    }

    public static Field getZr() {
        return pairingA1.getZr();
    }

    public static Pairing getPairing() {
        return pairingA1;
    }

    public static Element doPairingA1(Element e1,Element e2){
        return pairingA1.pairing(e1, e2);
    }
}
