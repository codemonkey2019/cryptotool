package com.cryptotool.cipher.asymmetric;


import com.cryptotool.block.AE;
import com.cryptotool.cipher.MyCipher;
import com.cryptotool.util.KeyUtils;
import lombok.Getter;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * MyCipher接口的实现类，封装了对称加密算法（除SM2）外的实现。
 */
public class AECipher implements MyCipher {
    private AE algorithm;
    private Cipher cipher;
    @Getter
    private PublicKey publicKey;
    @Getter
    private PrivateKey privateKey;

    public AECipher(AE algo, byte[] privateKey, byte[] publicKey) throws Exception {
        this.algorithm = algo;
        this.privateKey= KeyUtils.toAEPrivateKey(algo,privateKey);
        this.publicKey= KeyUtils.toAEPublicKey(algo,publicKey);
        this.cipher=javax.crypto.Cipher.getInstance(algorithm.toString(),"BC");
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
    public void encryptFile(String inPath, String outPath) {
        throw new RuntimeException("不支持使用公钥加密算法进行文件加解密");
    }

    @Override
    public void decryptFile(String inPath, String outPath){
        throw new RuntimeException("不支持使用公钥加密算法进行文件加解密");
    }
}
