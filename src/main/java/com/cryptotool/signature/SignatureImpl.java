package com.cryptotool.signature;

import com.cryptotool.block.SIG;
import com.cryptotool.util.OperateKey;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.security.*;

/**
 * @author L
 * @date 2019-10-12 14:06
 * @desc
 **/
public class SignatureImpl implements MySignature {
    private SIG algorithm;
    private java.security.Signature signature;
    @Getter
    private PublicKey publicKey;
    @Getter
    private PrivateKey privateKey;

    public SignatureImpl(SIG algorithm, byte[] publicKey, byte[] privateKey) {
        this.algorithm = algorithm;

        this.publicKey = OperateKey.toSigPublicKey(algorithm, publicKey);
        this.privateKey = OperateKey.toSigPrivateKey(algorithm, privateKey);
        try {
            this.signature= java.security.Signature.getInstance(algorithm.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    @NotNull
    @Override
    public byte[] sign(byte[] data) {
        try {
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean verify(byte[] data, byte[] sign) {
        try {
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(sign);
        } catch (InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return false;
    }
}
