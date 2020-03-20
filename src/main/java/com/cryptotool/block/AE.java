package com.cryptotool.block;

/**
 * 支持的非对称加密算法的部分枚举
 */
public enum AE {
    SM2("SM2"),
    ELGAMAL("ElGamal"),
    RSA("RSA");

    private final String algo;

    private AE(String mode) {
        this.algo = mode;
    }

    @Override
    public String toString() {
        return algo;
    }
}
