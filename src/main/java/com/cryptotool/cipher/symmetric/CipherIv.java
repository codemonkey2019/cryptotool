package com.cryptotool.cipher.symmetric;


import com.cryptotool.block.Padding;
import com.cryptotool.block.Pattern;
import com.cryptotool.block.SE;
import com.cryptotool.cipher.MyCipher;
import com.cryptotool.util.KeyUtils;
import com.cryptotool.util.MyUtils;
import lombok.Getter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jetbrains.annotations.NotNull;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.SecureRandom;
import java.security.Security;

/**
 * MyCipher接口的实现类，封装了实现对称加密算法的非确定性模式的实现
 */
public class CipherIv implements MyCipher {

    private SE algorithm;
    private Pattern mode;
    private Padding padding;
    private Cipher cipherForIv;
    private Cipher cipherForNoIv;
    private static SecureRandom secureRandom = new SecureRandom();
    @Getter
    private SecretKey key;
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public CipherIv(SE algorithm, Pattern mode, Padding padding, byte[] key) throws Exception {
        if (Pattern.ECB.equals(mode)){
            throw new Exception("错误的工作模式!");
        }
        this.algorithm = algorithm;
        this.mode = mode;
        this.padding = padding;
        this.key= KeyUtils.toSecretKey(algorithm,key);
        this.cipherForIv = javax.crypto.Cipher.getInstance(MyUtils.prase(algorithm, mode, padding));
        this.cipherForNoIv = javax.crypto.Cipher.getInstance(MyUtils.prase(algorithm, Pattern.ECB, Padding.NoPadding));
    }

    /**
     * 加密算法
     *
     * @param data 明文数据
     * @return 密文数据
     * @throws Exception
     */
    @Override
    public byte[] encrypt(@NotNull byte[] data){

        try {
            this.cipherForNoIv.init(1,key);
            byte[] iv = new byte[cipherForIv.getBlockSize()];
            secureRandom.nextBytes(iv);

            byte[] en_iv = cipherForNoIv.doFinal(iv);
            this.cipherForIv.init(1, key, new IvParameterSpec(iv));
            byte[] endata = cipherForIv.doFinal(data);
            byte[] out = new byte[en_iv.length+endata.length];

            System.arraycopy(en_iv, 0, out, 0, en_iv.length);
            System.arraycopy(endata, 0, out, en_iv.length, endata.length);

            return out;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * 解密算法
     *
     * @param data 明文数据
     * @return 密文数据
     * @throws Exception
     */
    @Override
    public byte[] decrypt(@NotNull byte[] data) {
        try {
            this.cipherForNoIv.init(2,key);
            byte[] en_iv = new byte[cipherForIv.getBlockSize()];
            System.arraycopy(data, 0, en_iv, 0, en_iv.length);
            byte[] iv = cipherForNoIv.doFinal(en_iv);

            byte[] src = new byte[data.length-en_iv.length];
            System.arraycopy(data, en_iv.length, src, 0, src.length);

            this.cipherForIv.init(2, key, new IvParameterSpec(iv));

            return this.cipherForIv.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * 文件加密
     * @param inPath
     * @param outPath
     * @throws Exception
     */
    @Override
    public void encryptFile(@NotNull String inPath, @NotNull String outPath) {
        File file = new File(inPath);
        if (file.exists()){
            try {
                this.cipherForNoIv.init(1,key);
                byte[] iv = new byte[cipherForIv.getBlockSize()];
                secureRandom.nextBytes(iv);
                byte[] en_iv = cipherForNoIv.doFinal(iv);
                this.cipherForIv.init(1, key,new IvParameterSpec(iv));

                InputStream is = new FileInputStream(file);
                CipherInputStream cis = new CipherInputStream(is, this.cipherForIv);

                OutputStream os = new FileOutputStream(outPath);
                BufferedOutputStream bos = new BufferedOutputStream(os);
                bos.write(en_iv);
                byte[] b = new byte[1024];
                int r;
                while ((r=cis.read(b))!=-1){
                    bos.write(b, 0, r);
                }
                cis.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            throw new RuntimeException("文件不存在");
        }

    }

    /**
     * 文件解密
     * @param inPath
     * @param outPath
     * @throws Exception
     */
    @Override
    public void decryptFile(@NotNull String inPath, @NotNull String outPath){
        File file = new File(inPath);
        if (file.exists()){
            try {
                this.cipherForNoIv.init(2,key);

                BufferedInputStream bif = new BufferedInputStream(new FileInputStream(inPath));
                byte[] en_iv = new byte[cipherForIv.getBlockSize()];
                bif.read(en_iv);
                byte[] iv = cipherForNoIv.doFinal(en_iv);

                this.cipherForIv.init(2, key,new IvParameterSpec(iv));
                FileOutputStream os = new FileOutputStream(outPath);
                CipherOutputStream cos = new CipherOutputStream(os, cipherForIv);

                byte[] b = new byte[1024];
                int r;
                while ((r=bif.read(b))!=-1){
                    cos.write(b, 0, r);
                }
                bif.close();
                cos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            throw new RuntimeException("文件不存在");
        }

    }

}
