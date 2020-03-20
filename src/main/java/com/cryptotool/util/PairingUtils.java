package com.cryptotool.util;

import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class PairingUtils {

    public static Field getG1A(){
        return getPairingA().getG1();
    }
    public static Field getG2A(){
        return getPairingA().getG2();
    }
    public static Field getZrA(){
        return getPairingA().getZr();
    }
    public static Field getG1A1(){
        return getPairingA1().getG1();
    }
    public static Field getG2A1(){
        return getPairingA1().getG2();
    }
    public static Field getZrA1(){
        return getPairingA1().getZr();
    }

    public static Pairing getPairingA(){
        return PairingFactory.getPairing("param/a.properties");
    }
    public static Pairing getPairingA1(){
        return PairingFactory.getPairing("param/a1.properties");
    }

}
