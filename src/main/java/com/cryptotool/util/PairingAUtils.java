package com.cryptotool.util;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class PairingAUtils {
    private static Pairing pairingA = PairingFactory.getPairing("param/a.properties");

    public static Element getRandomElementOfG1(){
        return getG1().newRandomElement();
    }

    public static Element getOneElementOfG1(){
        return getG1().newOneElement();
    }

    public static Element getRandomElementOfG2(){
        return getG2().newRandomElement();
    }

    public static Element getOneElementOfG2(){
        return getG2().newOneElement();
    }
    public static Element getRandomElementOfZr(){
        return getZr().newRandomElement();
    }

    public static Element getOneElementOfZr(){
        return getZr().newOneElement();
    }

    public static Field getG1() {
        return pairingA.getG1();
    }

    public static Field getG2() {
        return pairingA.getG2();
    }

    public static Field getZr() {
        return pairingA.getZr();
    }


    public static Pairing getPairing() {
        return pairingA;
    }
    public static Element doPairing(Element e1,Element E2){
        return pairingA.pairing(e1, E2);
    }
}
