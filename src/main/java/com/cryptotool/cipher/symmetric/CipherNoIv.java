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
import java.io.*;
import java.security.Security;

/**
 * MyCipher接口的实现类，封装了实现对称加密算法的确定性模式的实现，即ECB模式
 */
public class CipherNoIv implements MyCipher {
    private SE algorithm;
    private Pattern mode;
    private Padding padding;
    private Cipher cipher;
    @Getter
    private SecretKey key;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    public CipherNoIv(SE algorithm, Pattern mode, Padding padding, byte[] key) throws Exception {
        if (!Pattern.ECB.equals(mode)){
            throw new Exception("错误的工作模式");
        }
        this.algorithm = algorithm;
        this.mode = mode;
        this.padding = padding;

        this.key= KeyUtils.toSecretKey(algorithm,key);
        this.cipher = Cipher.getInstance(MyUtils.prase(algorithm, mode, padding));
    }

    /**
     * 加密算法
     *
     * @param data 明文数据
     * @return 密文数据
     * @throws Exception
     */
    @Override
    @NotNull
    public byte[] encrypt(@NotNull byte[] data) {
        try {
            this.cipher.init(1,key);
            byte[] out = this.cipher.doFinal(data);
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
    @NotNull
    public byte[] decrypt(@NotNull byte[] data){
        try {
            this.cipher.init(2,key);
            byte[] out = this.cipher.doFinal(data);
            return out;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     *
     * @param inPath
     * @param outPath
     * @throws Exception
     */
    @Override
    public void encryptFile(@NotNull String inPath, @NotNull String outPath) {
        File file = new File(inPath);
        if (file.exists()){
            try {
                this.cipher.init(1, key);

                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

                FileOutputStream os = new FileOutputStream(outPath);
                CipherOutputStream cos = new CipherOutputStream(os, this.cipher);
                BufferedOutputStream bos = new BufferedOutputStream(cos);
                byte[] b = new byte[1024];
                int r;
                while ((r=bis.read(b))!=-1){
                    bos.write(b,0,r);
                }
                bis.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            throw new RuntimeException("文件不存在");
        }

    }

    /**
     *
     * @param inPath
     * @param outPath
     * @throws Exception
     */
    @Override
    public void decryptFile(@NotNull String inPath, @NotNull String outPath) {
        File file = new File(inPath);
        if (file.exists()){
            try {
                this.cipher.init(2,key);
                InputStream is = new FileInputStream(file);
                CipherInputStream cis = new CipherInputStream(is,this.cipher);
                BufferedInputStream bis = new BufferedInputStream(cis);

                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outPath));
                byte[] b = new byte[1024];
                int r;
                while ((r=bis.read(b))!=-1){
                    bos.write(b,0,r);
                }
                bis.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            throw new RuntimeException("文件不存在");
        }
    }
}
