package com.liu.cryptotool.block;

public enum DigAlgorithm {
    SM3("SM3"),
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA512("SHA-512"),
    MD2("MD2"),
    MD5("MD5");

    private String algo;
    private DigAlgorithm(String algo){
        this.algo=algo;
    }
    public String toString(){
        return algo;
    }
}
