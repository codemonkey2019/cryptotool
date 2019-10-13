package com.liu.cryptotool.block;

/**
 * @author L
 * @date 2019-10-12 13:58
 * @desc
 **/
public enum SigAlgorithm {
    NONEWITHRSA("NONEwithRSA"),
    SHA256WITHRSA("SHA256withRSA"),
    SHA512WITHRSA("SHA512withRSA"),
    SHA1WITHDSA("SHA1withDSA"),
    NONEWITHSM2("NONEWITHSM2");

    private final String algo;

    private SigAlgorithm(String mode) {
        this.algo = mode;
    }

    @Override
    public String toString() {
        return algo;
    }
}
