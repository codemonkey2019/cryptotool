package com.liu.cryptotool.digests;

public interface MyDigest {
    byte[] getHash(byte[] data);

    String getHash(String data);

    byte[] getHashFromFile(String path);
}
