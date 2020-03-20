package com.cryptotool.util;

import java.util.Base64;

public class MyBase64 {
    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();
    public static String toBase64String(byte[] data){
        return encoder.encodeToString(data);
    }
    public static byte[] toByteArray(String base64Data){
        return decoder.decode(base64Data);
    }
}
