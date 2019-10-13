package com.liu.cryptotool.control;

import com.liu.cryptotool.asymmetric.AECipher;
import com.liu.cryptotool.asymmetric.SM2Cipher;
import com.liu.cryptotool.block.AEAlgorithm;
import com.liu.cryptotool.block.Padding;
import com.liu.cryptotool.block.Pattern;
import com.liu.cryptotool.block.SEAlgorithm;
import com.liu.cryptotool.symmetric.CipherIv;
import com.liu.cryptotool.symmetric.CipherNoIv;
import org.jetbrains.annotations.NotNull;

/**
 * 获得Cipher的工厂类，根据传入的参数自动返回对应的Cipher实现类
 */
public class CipherFactory {
    private CipherFactory() {
    }

    /**
     * 获取Cipher的工厂方法
     *
     * @param algorithm 加密算法
     * @param mode      工作模式
     * @param padding   填充模式
     * @return
     * @throws Exception
     */
    @NotNull
    public static MyCipher getSECipher(SEAlgorithm algorithm, Pattern mode, Padding padding, byte[] key) {

        try {
            if (Pattern.ECB.equals(mode)) {
                return new CipherNoIv((SEAlgorithm) algorithm, mode, padding, key);
            } else {
                return new CipherIv((SEAlgorithm) algorithm, mode, padding, key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static MyCipher getAECipher(AEAlgorithm aeAlgorithm, byte[] privateKey, byte[] publicKey) {

        if (aeAlgorithm == AEAlgorithm.SM2) {
            return new SM2Cipher(privateKey, publicKey);
        } else {
            return new AECipher(aeAlgorithm, privateKey, publicKey);
        }

    }


}
