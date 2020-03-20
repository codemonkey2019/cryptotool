package com.cryptotool.block;

/**
 * 支持的对称加密算法的枚举
 */
public enum SE {

    AES("AES"),
    DES("DES"),
    SM4("SM4");

    private final String algo;

    private SE(String mode) {
        this.algo = mode;
    }

    @Override
    public String toString() {
        return algo;
    }
}
