package com.cryptotool.cipher.asymmetric;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author L
 * @date 2019-10-12 13:32
 * @desc
 **/
@AllArgsConstructor
public class AEKeyPair {
    @Getter
    private byte[] privateKey;
    @Getter
    private byte[] publicKey;
}
