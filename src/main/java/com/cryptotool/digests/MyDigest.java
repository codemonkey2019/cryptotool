package com.cryptotool.digests;

public interface MyDigest {
    byte[] getDigest(byte[] data);

    String getDigest(String data);

    byte[] getDigestOfFile(String path);
}
