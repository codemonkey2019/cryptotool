package com.liu.cryptotool.signature;


import com.liu.cryptotool.block.SIG;

/**
 * @author L
 * @date 2019-10-12 14:46
 * @desc
 **/
public class SignatureFactory {
    private SignatureFactory(){ }

    public static MySignature getSignature(SIG sigAlgorithm, byte[] privateKey, byte[] publicKey){
        if (sigAlgorithm== SIG.NONEWITHSM2){
            return new SM2Signature(publicKey, privateKey);
        }else {
            return new SignatureImpl(sigAlgorithm, publicKey, privateKey);
        }
    }
}
