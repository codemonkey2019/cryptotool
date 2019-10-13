package com.liu.cryptotool.utils;

import com.liu.cryptotool.block.Padding;
import com.liu.cryptotool.block.Pattern;
import com.liu.cryptotool.block.SEAlgorithm;
import com.liu.cryptotool.block.SigAlgorithm;

public class MyUtils {
    /**
     * 整合加解密模式
     * @param algorithm
     * @param mode
     * @param padding
     * @return
     */
    public static String prase(SEAlgorithm algorithm,
                               Pattern mode,
                               Padding padding){
        return algorithm.toString()+"/"
                +mode.toString()+"/"
                +padding.toString();

    }
     public static String fetch(SigAlgorithm sigAlgorithm){
        if (sigAlgorithm.toString().indexOf("RSA")!=-1){
            return "RSA";
        }else if (sigAlgorithm.toString().indexOf("DSA")!=-1){
            return "DSA";
        }else {
            return "SM2";
         }
     }
}
