package com.cryptotool.util;

import com.cryptotool.block.DIG;
import com.cryptotool.digests.DigestFactory;
import com.cryptotool.digests.MyDigest;

import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class MyStringUtils {
    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();
    private static MyDigest myDigest = DigestFactory.getDigest(DIG.SHA512);
    private static Random random = new Random();
    private static String v = "stringForRandom";

    public static String encodeToBase64String(byte[] data) {
        return encoder.encodeToString(data);
    }

    public static byte[] encodeToBase64Array(byte[] data) {
        return encoder.encode(data);
    }

    public static byte[] decodeFromBase64tring(String base64Data) {
        return decoder.decode(base64Data);
    }

    public static byte[] decodeFromBase64Array(byte[] base64Data) {
        return decoder.decode(base64Data);
    }

    /**
     * 输出一个给定长度的随机字节序列
     * 最大支持512位，64字节
     *
     * @param length 随机序列的字节长度 <=64字节
     * @return 随机字节数组
     */
    public static byte[] getRandomByte(int length) {
        if (length>64) {
            throw new RuntimeException("长度超过64字节，512比特");
        }
        int i = random.nextInt();
        String s = v+i;
        byte[] hash = myDigest.getDigest(s.getBytes());
        return Arrays.copyOf(hash,length);
    }

}
