package com.cryptotool.digests;


import com.cryptotool.block.DIG;
import lombok.Getter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.*;
import java.util.Base64;

public class DigestImpl implements MyDigest {
    @Getter
    private DIG algo;
    private MessageDigest md;

    public DigestImpl(DIG algo){
        this.algo=algo;
        try {
            this.md=MessageDigest.getInstance(algo.toString(),"BC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    @Override
    public byte[] getDigest(byte[] data) {
        return md.digest(data);
    }

    @Override
    public String getDigest(String data) {
        return Base64.getEncoder().encodeToString(getDigest(data.getBytes()));
    }
    @NotNull
    @Override
    public byte[] getDigestOfFile(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            DigestInputStream dis = new DigestInputStream(fis,md);
            return dis.getMessageDigest().digest();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
