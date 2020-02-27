package com.liu.cryptotool.asymmetric;


import com.liu.cryptotool.block.AEAlgorithm;
import com.liu.cryptotool.control.MyCipher;
import com.liu.cryptotool.control.OperateKey;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class AECipher implements MyCipher {
    private AEAlgorithm algorithm;
    private javax.crypto.Cipher cipher;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public AECipher(AEAlgorithm algo, byte[] privateKey, byte[] publicKey){
        this.algorithm = algo;
        this.privateKey= OperateKey.toAEPrivateKey(algo,privateKey);
        this.publicKey=OperateKey.toAEPublicKey(algo,publicKey);
        try {
            this.cipher=javax.crypto.Cipher.getInstance(algorithm.toString(),"BC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] encrypt(byte[] data) {
        if (publicKey == null) {
            throw new RuntimeException("公钥为空");
        }
        try {
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE,publicKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public byte[] decrypt(byte[] data){
        if (privateKey == null) {
            throw new RuntimeException("私钥为空");
        }
        try {
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE,privateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public void encrypt(String inPath, String outPath) {
        throw new RuntimeException("公钥加密不支持文件加解密");
    }

    @Override
    public void decrypt(String inPath, String outPath){
        throw new RuntimeException("公钥加密不支持文件加解密");
    }
}
