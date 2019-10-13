package com.liu.cryptotool.control;


import com.liu.cryptotool.block.AEAlgorithm;
import com.liu.cryptotool.block.SEAlgorithm;
import com.liu.cryptotool.block.SigAlgorithm;
import com.liu.cryptotool.utils.AEKeyPair;
import com.liu.cryptotool.utils.BCECUtil;
import com.liu.cryptotool.utils.MyUtils;
import com.liu.cryptotool.utils.SM2Util;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jetbrains.annotations.NotNull;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author L
 * @date 2019-09-20 21:37
 * @desc
 **/
public class OperateKey {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 获取对称密钥的字节编码
     *
     * @param algo
     * @return
     */
    public static byte[] getSecretKey(SEAlgorithm algo) {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(algo.toString(), "BC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return keyGenerator.generateKey().getEncoded();
    }


    /**
     * 转换密钥，将密钥字节数组转为密钥对象
     *
     * @param algo
     * @param key
     * @return
     */
    public static SecretKey toSecretKey(SEAlgorithm algo, byte[] key) {
        SecretKey secretKey = new SecretKeySpec(key, algo.toString());
        return secretKey;
    }

    /**
     * 获取SM2的密钥对，存放在Map里，键为private和public
     *
     * @return
     */
    private static AEKeyPair getSM2Key() {
        AsymmetricCipherKeyPair keyPair = SM2Util.generateKeyPairParameter();
        ECPrivateKeyParameters priKey = (ECPrivateKeyParameters) keyPair.getPrivate();
        ECPublicKeyParameters pubKey = (ECPublicKeyParameters) keyPair.getPublic();
        byte[] priKeyPkcs8Der = BCECUtil.convertECPrivateKeyToPKCS8(priKey, pubKey);
        byte[] pubKeyX509Der = BCECUtil.convertECPublicKeyToX509(pubKey);
        return new AEKeyPair(priKeyPkcs8Der,pubKeyX509Der);
    }

    /**
     * 转化SM2的公钥
     * @param publicKey
     * @return
     */
    @NotNull
    public static BCECPublicKey toSM2PublicKey(byte[] publicKey){
        if(publicKey==null){

            return null;
        }
        try {
            BCECPublicKey sm2PubKey = BCECUtil.convertX509ToECPublicKey(publicKey);
            return sm2PubKey;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换SM2的私钥
     * @param privateKey
     * @return
     */
    @NotNull
    public static BCECPrivateKey toSM2PrivateKey(byte[] privateKey){
        if(privateKey==null){
            return null;
        }
        try {
            BCECPrivateKey sm2PriKey = BCECUtil.convertPKCS8ToECPrivateKey(privateKey);
            return sm2PriKey;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AEKeyPair getKeyPair(String algo){
        if (algo.equals("SM2")){
            return getSM2Key();
        }
        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance(algo);
            KeyPair keyPair = generator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            return new AEKeyPair(privateKey.getEncoded(),publicKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static AEKeyPair getKeyPair(AEAlgorithm algo) {
        return getKeyPair(algo.toString());
    }

    public static AEKeyPair getSigKeyPair(SigAlgorithm algo) {
        return getKeyPair(MyUtils.fetch(algo));
    }


    @NotNull
    public static PublicKey toPublicKey(AEAlgorithm algo,byte[] pub) {
        return toPublicKey(algo.toString(), pub);
    }

    @NotNull
    public static PrivateKey toPrivateKey(AEAlgorithm algo, byte[] pri) {
        return toPrivateKey(algo.toString(), pri);
    }
    @NotNull
    public static PublicKey toPublicKey(String algo,byte[] pub) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pub);
            KeyFactory factory = KeyFactory.getInstance(algo);
            PublicKey publicKey = factory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NotNull
    public static PrivateKey toPrivateKey(String algo, byte[] pri) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pri);
            KeyFactory factory = KeyFactory.getInstance(algo);
            PrivateKey privateKey = factory.generatePrivate(keySpec);
            return privateKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
}
