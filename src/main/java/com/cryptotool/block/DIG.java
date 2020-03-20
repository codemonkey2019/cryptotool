package com.cryptotool.block;

/**
 * 支持的消息摘要算法的部分枚举
 */
public enum DIG {
    SM3("SM3"),
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA512("SHA-512"),
    MD2("MD2"),
    MD5("MD5");

    private String algo;
    private DIG(String algo){
        this.algo=algo;
    }
    public String toString(){
        return algo;
    }
}
