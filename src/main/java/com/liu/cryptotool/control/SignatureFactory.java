package com.liu.cryptotool.control;


import com.liu.cryptotool.block.SigAlgorithm;
import com.liu.cryptotool.signature.SM2Signature;
import com.liu.cryptotool.signature.MySignature;
import com.liu.cryptotool.signature.SignatureImpl;

/**
 * @author L
 * @date 2019-10-12 14:46
 * @desc
 **/
public class SignatureFactory {
    private SignatureFactory(){ }

    public static MySignature getSignature(SigAlgorithm sigAlgorithm, byte[] privateKey, byte[] publicKey){
        if (sigAlgorithm==SigAlgorithm.NONEWITHSM2){
            return new SM2Signature(publicKey, privateKey);
        }else {
            return new SignatureImpl(sigAlgorithm, publicKey, privateKey);
        }
    }
}
