package com.liu.cryptotool.signature;

import com.liu.cryptotool.block.SigAlgorithm;
import com.liu.cryptotool.control.OperateKey;
import com.liu.cryptotool.utils.MyUtils;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.security.*;

/**
 * @author L
 * @date 2019-10-12 14:06
 * @desc
 **/
public class SignatureImpl implements MySignature {
    private SigAlgorithm algorithm;
    private java.security.Signature signature;
    @Getter
    private PublicKey publicKey;
    @Getter
    private PrivateKey privateKey;

    public SignatureImpl(SigAlgorithm algorithm, byte[] publicKey, byte[] privateKey) {
        this.algorithm = algorithm;

        this.publicKey = OperateKey.toPublicKey(MyUtils.fetch(algorithm), publicKey);
        this.privateKey = OperateKey.toPrivateKey(MyUtils.fetch(algorithm), privateKey);
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
