package com.cryptotool.util;

import com.cryptotool.block.Padding;
import com.cryptotool.block.Pattern;
import com.cryptotool.block.SE;
import com.cryptotool.block.SIG;

public class MyUtils {
    /**
     * 解析签名枚举得到签名算法
     *
     * @param sigAlgorithm
     * @return
     */
    public static String fetch(SIG sigAlgorithm) {
        if (sigAlgorithm.toString().indexOf("RSA") != -1) {
            return "RSA";
        } else if (sigAlgorithm.toString().indexOf("DSA") != -1) {
            return "DSA";
        } else {
            return "SM2";
        }
    }

    /**
     * 整合加解密模式
     *
     * @param algorithm
     * @param mode
     * @param padding
     * @return
     */
    public static String prase(SE algorithm,
                               Pattern mode,
                               Padding padding) {
        return algorithm.toString() + "/"
                + mode.toString() + "/"
                + padding.toString();

    }
}
