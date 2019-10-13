package com.liu.cryptotool.block;

public enum SEAlgorithm {

    AES("AES"),
    DES("DES"),
    SM4("SM4");

    private final String algo;

    private SEAlgorithm(String mode) {
        this.algo = mode;
    }

    @Override
    public String toString() {
        return algo;
    }
}
