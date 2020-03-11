package com.liu.cryptotool.digests;

public interface MyDigest {
    byte[] getDig(byte[] data);

    String getDig(String data);

    byte[] getDigOfFile(String path);
}
