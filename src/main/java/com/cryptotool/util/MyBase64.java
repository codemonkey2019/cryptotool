package com.cryptotool.util;

import java.util.Base64;

public class MyBase64 {
    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();

    public static String encodeToString(byte[] data){
        return encoder.encodeToString(data);
    }

    public static byte[] encodeToArray(byte[] data){
        return encoder.encode(data);
    }

    public static byte[] decodeFromString(String base64Data){
        return decoder.decode(base64Data);
    }
    public static byte[] decodeFromArray(byte[] base64Data){
        return decoder.decode(base64Data);
    }

}
