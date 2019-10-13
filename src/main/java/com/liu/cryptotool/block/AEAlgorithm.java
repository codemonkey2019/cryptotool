package com.liu.cryptotool.block;

public enum AEAlgorithm {
    SM2("SM2"),
    ELGAMAL("ElGamal"),
    RSA("RSA");

    private final String algo;

    private AEAlgorithm(String mode) {
        this.algo = mode;
    }

    @Override
    public String toString() {
        return algo;
    }
}
