package com.liu.cryptotool.symmetric;

import com.liu.cryptotool.block.Padding;
import com.liu.cryptotool.block.Pattern;
import com.liu.cryptotool.block.SEAlgorithm;
import com.liu.cryptotool.control.OperateKey;
import com.liu.cryptotool.utils.MyUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jetbrains.annotations.NotNull;

import com.liu.cryptotool.control.MyCipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.Security;

/**
 * 确定性加密算法类，如：ECB
 */
public class CipherNoIv implements MyCipher {
    private SEAlgorithm algorithm;
    private Pattern mode;
    private Padding padding;
    private javax.crypto.Cipher cipher;
    private SecretKey key;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    public CipherNoIv(SEAlgorithm algorithm, Pattern mode, Padding padding,byte[] key) throws Exception {
        if (!Pattern.ECB.equals(mode)){
            throw new Exception("错误的工作模式");
        }
        this.algorithm = algorithm;
        this.mode = mode;
        this.padding = padding;
        this.key= OperateKey.toSecretKey(algorithm,key);
        this.cipher = javax.crypto.Cipher.getInstance(MyUtils.prase(algorithm, mode, padding));
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
    public void encrypt(@NotNull String inPath, @NotNull String outPath) {
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
    public void decrypt(@NotNull String inPath, @NotNull String outPath) {
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
