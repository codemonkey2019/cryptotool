package com.cryptotool.block;

/**
 * 支持的数字签名算法的枚举
 **/
public enum SIG {
    NONEWITHRSA("NONEwithRSA"),
    SHA256WITHRSA("SHA256withRSA"),
    SHA512WITHRSA("SHA512withRSA"),
    SHA1WITHDSA("SHA1withDSA"),
    NONEWITHSM2("NONEWITHSM2");

    private final String algo;

    private SIG(String mode) {
        this.algo = mode;
    }

    @Override
    public String toString() {
        return algo;
    }
}
