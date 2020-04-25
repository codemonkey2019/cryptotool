package com.cryptotool.cipher;

import com.cryptotool.cipher.asymmetric.AECipher;
import com.cryptotool.cipher.asymmetric.SM2;
import com.cryptotool.block.AE;
import com.cryptotool.block.Padding;
import com.cryptotool.block.Pattern;
import com.cryptotool.block.SE;
import com.cryptotool.cipher.symmetric.CipherIv;
import com.cryptotool.cipher.symmetric.CipherNoIv;
import org.jetbrains.annotations.NotNull;

/**
 * 获得MyCipher接口实现类对象的工厂类，根据传入的参数自动返回对应的MyCipher实现类
 */
public class MyCipherFactory {
    private MyCipherFactory() {
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
    public static MyCipher getSECipher(SE algorithm, Pattern mode, Padding padding, byte[] key) {

        try {
            if (Pattern.ECB.equals(mode)) {
                return new CipherNoIv((SE) algorithm, mode, padding, key);
            } else {
                return new CipherIv((SE) algorithm, mode, padding, key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MyCipher getAECipher(AE aeAlgorithm, byte[] privateKey, byte[] publicKey) {

        if (aeAlgorithm == AE.SM2) {
            return new SM2(privateKey, publicKey);
        } else {
            MyCipher cipher = null;
            try {
               cipher= new AECipher(aeAlgorithm, privateKey, publicKey);
            }catch (Exception e){
                e.printStackTrace();
            }
            return cipher;
        }
    }


}
