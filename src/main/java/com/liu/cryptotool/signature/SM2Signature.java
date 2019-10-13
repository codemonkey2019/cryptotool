package com.liu.cryptotool.signature;

import com.liu.cryptotool.control.OperateKey;
import com.liu.cryptotool.utils.SM2Util;
import lombok.Getter;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.jetbrains.annotations.NotNull;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author L
 * @date 2019-10-12 14:26
 * @desc
 **/
public class SM2Signature implements MySignature {
    @Getter
    private BCECPublicKey publicKey;
    @Getter
    private BCECPrivateKey privateKey;

    public SM2Signature(byte[] publicKey, byte[] privateKey) {
        this.publicKey = OperateKey.toSM2PublicKey(publicKey);
        this.privateKey = OperateKey.toSM2PrivateKey(privateKey);
    }
    @NotNull
    @Override
    public byte[] sign(byte[] data) {
        try {
            byte[] out  = SM2Util.sign(privateKey, data);
            return out;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (CryptoException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean verify(byte[] data, byte[] sign) {
        return SM2Util.verify(publicKey, data, sign);
    }
}
