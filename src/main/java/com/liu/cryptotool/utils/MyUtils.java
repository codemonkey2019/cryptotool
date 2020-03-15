package com.liu.cryptotool.utils;

import com.liu.cryptotool.block.Padding;
import com.liu.cryptotool.block.Pattern;
import com.liu.cryptotool.block.SE;
import com.liu.cryptotool.block.SIG;

public class MyUtils {

    private static String hexStr =  "0123456789ABCDEF";
    private static String[] binaryArray =
            {"0000","0001","0010","0011",
                    "0100","0101","0110","0111",
                    "1000","1001","1010","1011",
                    "1100","1101","1110","1111"};
    /**
     * 将byte数组转换为十六进制字符输出
     * @param bytes 待转的byte数组
     * @return 16进制字符串
     */
    public static String binaryToHexString(byte[] bytes){

        StringBuilder builder = new StringBuilder();

        for(int i=0;i<bytes.length;i++){
            //字节高4位
            builder.append(hexStr.charAt((bytes[i]&0xF0)>>4));
            //字节低4位
            builder.append(hexStr.charAt(bytes[i]&0x0F));
        }
        return builder.toString();
    }
    /**
     *  将十六进制字符串转换为字节数组
     * @param hexString 16进制字符串
     * @return byte数组
     */
    public static byte[] hexStringToBinary(String hexString){
        //hexString的长度对2取整，作为bytes的长度
        int len = hexString.length()/2;
        byte[] bytes = new byte[len];
        byte high = 0;//字节高四位
        byte low = 0;//字节低四位

        for(int i=0;i<len;i++){
            //右移四位得到高位
            high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);
            low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));
            bytes[i] = (byte) (high|low);//高低位做或运算
        }
        return bytes;
    }

    /**
     * 整合加解密模式
     * @param algorithm
     * @param mode
     * @param padding
     * @return
     */
    public static String prase(SE algorithm,
                               Pattern mode,
                               Padding padding){
        return algorithm.toString()+"/"
                +mode.toString()+"/"
                +padding.toString();

    }

    /**
     * 解析签名枚举得到签名算法
     * @param sigAlgorithm
     * @return
     */
     public static String fetch(SIG sigAlgorithm){
        if (sigAlgorithm.toString().indexOf("RSA")!=-1){
            return "RSA";
        }else if (sigAlgorithm.toString().indexOf("DSA")!=-1){
            return "DSA";
        }else {
            return "SM2";
         }
     }
}
