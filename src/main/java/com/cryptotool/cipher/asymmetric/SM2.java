package com.cryptotool.cipher.asymmetric;

import com.cryptotool.cipher.MyCipher;
import com.cryptotool.util.KeyUtils;
import com.cryptotool.util.SM2Util;
import lombok.Getter;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

/**
 * 国密SM2算法的实现
 */
public class SM2 implements MyCipher {
    @Getter
    private BCECPublicKey publicKey;
    @Getter
    private BCECPrivateKey privateKey;

    public SM2(byte[] privateKey, byte[] publicKey){
        this.privateKey= KeyUtils.toSM2PrivateKey(privateKey);
        this.publicKey= KeyUtils.toSM2PublicKey(publicKey);
    }

    @Override
    public byte[] encrypt(byte[] data){
        if (publicKey == null) {
            throw new RuntimeException("公钥为空");
        }
        byte[] out = new byte[0];
        try {
            out  = SM2Util.encrypt(publicKey,data);

        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public byte[] decrypt(byte[] data){
        if (privateKey == null) {
            throw new RuntimeException("私钥为空");
        }
        byte[] out = new byte[0];
        try {
            out  = SM2Util.decrypt(privateKey,data);

        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
        }
        return out;
    }
    @Deprecated
    @Override
    public void encryptFile(String inPath, String outPath) {
        throw new RuntimeException("公钥不支持文件加解密");
    }

    @Deprecated
    @Override
    public void decryptFile(String inPath, String outPath){
        throw new RuntimeException("公钥不支持文件加解密");
    }
}
