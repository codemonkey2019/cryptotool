package com.cryptotool.digests;

import com.cryptotool.block.DIG;

public class DigestFactory {
    private DigestFactory(){};

    public static MyDigest getDigest(DIG algo){
        return new DigestImpl(algo);
    }
}
