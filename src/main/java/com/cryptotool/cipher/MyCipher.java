package com.cryptotool.cipher;

/**
 * 加解密算法的总接口
 */
public interface MyCipher {

    /**
     * 加密算法
     *
     * @param data 明文数据
     * @return 密文数据
     * @throws Exception
     */
    byte[] encrypt(byte[] data);

    /**
     * 解密算法
     *
     * @param data 明文数据
     * @return 密文数据
     * @throws Exception
     */
    byte[] decrypt(byte[] data);

    /**
     * 文件加密
     *
     * @param inPath
     * @param outPath
     * @throws Exception
     */
    void encrypt(String inPath, String outPath);

    /**
     * 文件解密
     *
     * @param inPath
     * @param outPath
     * @throws Exception
     */
    void decrypt(String inPath, String outPath);
}
