package com.cryptotool.signature;

public interface MySignature {
    byte[] sign(byte[] data);
    boolean verify(byte[] data, byte[] sign);
}
