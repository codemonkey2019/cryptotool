package com.liu.cryptotool.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
public class SigKeyPair {
    @Getter
    private byte[] privateKey;
    @Getter
    private byte[] publicKey;
}
