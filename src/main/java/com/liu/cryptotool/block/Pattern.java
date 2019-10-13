package com.liu.cryptotool.block;

/**
 * Portion of com.liu.cryptotools.block cipher mode of operation.
 * For more modes, see <a href="https://www.bouncycastle.org/specifications.html">bouncycastle.org</a>
 */
public enum Pattern {
    ECB("ECB"),
    CBC("CBC"),
//    PCBC("PCBC"),
    CTR("CTR"),
//    CTS("CTS"),
    CFB("CFB"),/* CFB8("CFB8"), CFB16("CFB16"), CFB32("CFB32"), CFB64("CFB64"), CFB128("CFB128"),
    OFB("OFB"), OFB8("OFB8"), OFB16("OFB16"), OFB32("OFB32"), OFB64("OFB64"), OFB128("OFB128")*/;

    private final String mode;

    private Pattern(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return mode;
    }
}
