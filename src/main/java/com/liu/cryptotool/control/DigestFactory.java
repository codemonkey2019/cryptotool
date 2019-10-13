package com.liu.cryptotool.control;

import com.liu.cryptotool.block.DigAlgorithm;
import com.liu.cryptotool.digests.MyDigest;
import com.liu.cryptotool.digests.DigestImpl;

public class DigestFactory {
    private DigestFactory(){};

    public static MyDigest getDigest(DigAlgorithm algo){
        return new DigestImpl(algo);
    }
}
