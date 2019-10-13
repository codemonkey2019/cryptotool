package com.liu.cryptotool.asymmetric;

import com.liu.cryptotool.control.MyCipher;
import com.liu.cryptotool.control.OperateKey;
import com.liu.cryptotool.utils.SM2Util;
import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

public class SM2Cipher implements MyCipher {
    @Getter@Setter
    private BCECPublicKey publicKey;
    @Getter@Setter
    private BCECPrivateKey privateKey;

    public SM2Cipher(byte[] privateKey, byte[] publicKey){
        this.privateKey= OperateKey.toSM2PrivateKey(privateKey);
        this.publicKey=OperateKey.toSM2PublicKey(publicKey);
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
    public void encrypt(String inPath, String outPath) {
        throw new RuntimeException("公钥不支持文件加解密");
    }

    @Deprecated
    @Override
    public void decrypt(String inPath, String outPath){
        throw new RuntimeException("公钥不支持文件加解密");
    }
}
