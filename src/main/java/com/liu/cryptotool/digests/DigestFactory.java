package com.liu.cryptotool.digests;

import com.liu.cryptotool.block.DIG;

public class DigestFactory {
    private DigestFactory(){};

    public static MyDigest getDigest(DIG algo){
        return new DigestImpl(algo);
    }
}
