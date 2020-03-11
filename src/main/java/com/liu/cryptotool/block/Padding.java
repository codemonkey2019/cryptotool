package com.liu.cryptotool.block;

/**
 * 堆对称加密支持的填充模式的枚举
 */
public enum Padding {
    NoPadding("NoPadding"),
    PKCS5("PKCS5Padding"), PKCS7("PKCS7Padding"),
    ISO10126("ISO10126Padding"), ISO10126_2("ISO10126-2Padding"),
    ISO7816_4("ISO7816-4Padding"), ISO9797_1("ISO9797-1Padding"),
    X923("X923Padding"), X9_23("X9.23Padding"),
    TBC("TBCPadding"),
    ZeroByte("ZeroBytePadding"),
    WithCTS("WithCTS"); // if used with ECB mode

    private final String padding;

    private Padding(String padding) {
        this.padding = padding;
    }

    @Override
    public String toString() {
        return padding;
    }
}
