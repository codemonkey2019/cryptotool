package com.cryptotool.util;


import com.cryptotool.block.AE;
import com.cryptotool.block.SE;
import com.cryptotool.block.SIG;
import com.cryptotool.cipher.asymmetric.AEKeyPair;
import com.cryptotool.signature.SigKeyPair;
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
public class KeyUtils {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 随机获取对称密钥的字节编码
     *
     * @param algo
     * @return
     */
    public static byte[] getSecretKey(SE algo) {
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
    public static byte[] getSecretKeyBySeed(SE algo, byte[] seed) {
        KeyGenerator keyGenerator = null;
        SecureRandom secureRandom = new SecureRandom(seed);
        try {
            keyGenerator = KeyGenerator.getInstance(algo.toString(), "BC");
            keyGenerator.init(secureRandom);
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
    public static SecretKey toSecretKey(SE algo, byte[] key) {
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
    private static AEKeyPair getSM2KeyBySeed(byte[] seed) {
        AsymmetricCipherKeyPair keyPair = SM2Util.generateKeyPairParameter(seed);
        ECPrivateKeyParameters priKey = (ECPrivateKeyParameters) keyPair.getPrivate();
        ECPublicKeyParameters pubKey = (ECPublicKeyParameters) keyPair.getPublic();
        byte[] priKeyPkcs8Der = BCECUtil.convertECPrivateKeyToPKCS8(priKey, pubKey);
        byte[] pubKeyX509Der = BCECUtil.convertECPublicKeyToX509(pubKey);
        return new AEKeyPair(priKeyPkcs8Der,pubKeyX509Der);
    }

    /**
     * 转化SM2的公钥，将字节数组形式的公钥转换为标准形式
     * @param publicKey
     * @return BCECPublicKey
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
     * 转换SM2的私钥，将字节数组形式的私钥转换为标准形式
     * @param privateKey
     * @return BCECPrivateKey
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

    /**
     * 根据算法名字符串获取一个非对称加密算法的密钥对
     * @param algo
     * @return
     */
    private static AEKeyPair getKeyPair(String algo){
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
    private static AEKeyPair getKeyPairBySeed(String algo, byte[] seed){
        if (algo.equals("SM2")){
            return getSM2KeyBySeed(seed);
        }
        KeyPairGenerator generator = null;
        SecureRandom secureRandom = new SecureRandom();
        try {
            generator = KeyPairGenerator.getInstance(algo);
            generator.initialize(2048,secureRandom);
            KeyPair keyPair = generator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            return new AEKeyPair(privateKey.getEncoded(),publicKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据算法名枚举获取一个非对称加密的密钥对
     * @param algo
     * @return
     */
    public static AEKeyPair getAEKeyPair(AE algo) {
        return getKeyPair(algo.toString());
    }
    public static AEKeyPair getAEKeyPairBySeed(AE algo,byte[] seed) {
        return getKeyPairBySeed(algo.toString(),seed);
    }

    /**
     * 根据签名算法枚举值的名获取签名密钥对
     * @param algo
     * @return
     */
    public static SigKeyPair getSigKeyPair(SIG algo) {
        AEKeyPair keyPair = getKeyPair(MyUtils.fetch(algo));
        return new SigKeyPair(keyPair.getPrivateKey(),keyPair.getPublicKey());
    }

    /**
     * 转换除SM2外其他算法的公钥位标准形式
     * @param algo 算法名
     * @param pub 公钥字节数组
     * @return PublicKey
     */
    @NotNull
    public static PublicKey toAEPublicKey(AE algo, byte[] pub) {
        return toPublicKey(algo.toString(), pub);
    }
    /**
     * 转换除SM2外其他算法的私钥位标准形式
     * @param algo 算法名
     * @param pri 私钥字节数组
     * @return PublicKey
     */
    @NotNull
    public static PrivateKey toAEPrivateKey(AE algo, byte[] pri) {
        return toPrivateKey(algo.toString(), pri);
    }
    /**
     * 转换签名算法的公钥为标准形式
     * @param algo 算法名
     * @param pub 公钥字节数组
     * @return PublicKey
     */
    @NotNull
    public static PublicKey toSigPublicKey(SIG algo, byte[] pub) {
        return toPublicKey(MyUtils.fetch(algo), pub);
    }
    /**
     * 转换签名算法的私钥为标准形式
     * @param algo 算法名
     * @param pri 私钥字节数组
     * @return PublicKey
     */
    @NotNull
    public static PrivateKey toSigPrivateKey(SIG algo, byte[] pri) {
        return toPrivateKey(MyUtils.fetch(algo), pri);
    }

    @NotNull
    private static PublicKey toPublicKey(String algo,byte[] pub) {
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
    private static PrivateKey toPrivateKey(String algo, byte[] pri) {
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
