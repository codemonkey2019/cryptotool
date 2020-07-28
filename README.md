# cryptool工具包使用手册

## 需要先在本地Maven仓库安装JPBC包(如果用不到双线性映射的工具类，也可以不装)

1. 下载JPBC

   https://sourceforge.net/projects/jpbc/files/jpbc_2_0_0/

2. 安装到本地maven仓库，在根目录运行以下命令（运行出现ERROR也不影响，只需要它的jpbc-api和jpbc-plaf两个jar包，也可以单独把这里俩jar包安装到本地库）

   mvn install -DskipTests 

## 支持的算法

## 1 对称加密算法（Symmetric Encryption (SE)）

```java
public enum SE {

    AES("AES"),
    DES("DES"),
    SM4("SM4");
```



## 2 非对称加密算法（Asymmetric Ancryption （AE））

```java
public enum AE {
    SM2("SM2"),
    ELGAMAL("ElGamal"),
    RSA("RSA");
```



## 3 哈希函数（Hash/Digest）

```java
public enum DIG {
    SM3("SM3"),
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA512("SHA-512"),
    MD2("MD2"),
    MD5("MD5");
```

## 4 数字签名算法(Digital  Signature)

```java
public enum SIG {
    NONEWITHRSA("NONEwithRSA"),
    SHA256WITHRSA("SHA256withRSA"),
    SHA512WITHRSA("SHA512withRSA"),
    SHA1WITHDSA("SHA1withDSA"),
    NONEWITHSM2("NONEWITHSM2");
```

注：其名称对应的意思，如 SHA256WITHRSA : 使用SHA256对消息做哈希，然后用RSA对哈希值签名（即先哈希再签名）

## 支持的对称加密算法的工作模式和填充模式

工作模式：常用ECB、CBC

```java
public enum Pattern {
    ECB("ECB"),
    CBC("CBC"),
    CTR("CTR"),
    CFB("CFB");
```

填充模式：常用PKCS5

```java
public enum Padding {
    NoPadding("NoPadding"),
    PKCS5("PKCS5Padding"), PKCS7("PKCS7Padding"),
    ISO10126("ISO10126Padding"), ISO10126_2("ISO10126-2Padding"),
    ISO7816_4("ISO7816-4Padding"), ISO9797_1("ISO9797-1Padding"),
    X923("X923Padding"), X9_23("X9.23Padding"),
    TBC("TBCPadding"),
    ZeroByte("ZeroBytePadding"),
    WithCTS("WithCTS");
```

## 快速开始

## 1.1 对称加密算法的实现（SM4算法举例）

```java
//随机获取一个密钥字节数组
byte[] key = KeyUtils.getSecretKey(SE.SM4);
//从算法工厂中获取SM4加密的MyCipher实例，并指定工作模式，填充模式和密钥
MyCipher cipher = MyCipherFactory.getSECipher(SE.SM4, Pattern.CBC, Padding.PKCS5,key);
//准备一个数据
byte[] data = "hello word".getBytes();
//加密数据
byte[] en_data = cipher.encrypt(data);
//解密数据
byte[] de_data = cipher.decrypt(en_data);
```

## 1.2 非对称加密算法实现(SM2算法举例)

```java
//获取SM2密钥对
AEKeyPair key = KeyUtils.getAEKeyPair(AE.SM2);
//从算法工厂中获取SM2加密的MyCipher实例
MyCipher cipher = MyCipherFactory.getAECipher(AE.SM2,key.getPrivateKey(),key.getPublicKey());
//准备数据
byte[] data = "hello word".getBytes();
//加密数据
byte[] en_data = cipher.encrypt(data);
//解密数据
byte[] de_data = cipher.decrypt(en_data);
```

## 1.3 哈希函数

```java
String str = "hello word";
//获取执行哈希函数的类MyDigest对象
MyDigest digest = DigestFactory.getDigest(DIG.SM3);
//两次哈希
String d = digest.getDigest(str);
String d1 = digest.getDigest(str);
//对比是否一样
Assert.assertFalse(!(d1.equals(d)));
```

## 1.4 数字签名

```java
//签名密钥对
SigKeyPair keyPair = KeyUtils.getSigKeyPair(SIG.NONEWITHSM2);
//根据算法名获取签名类对象
MySignature signature = SignatureFactory.getSignature(SIG.NONEWITHSM2, keyPair.getPrivateKey(), keyPair.getPublicKey());
//准备数据
byte[] data = "hello word".getBytes();
//计算签名
byte[] sign = signature.sign(data);
//验证签名
boolean b = signature.verify(data, sign);
```

## 1.5 Base64 编码

```java
byte[] data = "aaaaa".getBytes();
//编码
byte[] base64 = MyBase64.encodeToArray(data);
//解码
byte[] dData = MyBase64.decodeFromArray(base64);
System.out.println(new String(dData));
```

## 1.6 字节数组与16进制字符串转换

```java
byte[] data = "aaaaaaa".getBytes();
String hex = HexUtils.binaryToHexString(data);
byte[] dData = HexUtils.hexStringToBinary(hex);
```

## 1.7 JPBC包的常用类封装

使用：

1. 获取一个Pairing对像

   ```java
   Pairing pairing = PairingAUtils.getPairing();//获取一个A曲线的Pairing
   ```

2. 可以直接获取域对象：G1、G2、Zr

   ```java 
   Field G1 = PairingAUtils.getG1();
   Field G2 = PairingAUtils.getG2();
   Field Z = PairingAUtils.getZr();
   ```

3. 直接获取对应域的随机元素

   ```java
   Element g = PairingAUtils.getRandomElementOfG1();
   Element g1 = PairingAUtils.getRandomElementOfG1();
   ```

4. 直接对两个域元素做pairing运算

   ```java
   Element g = PairingAUtils.getRandomElementOfG1();
   Element g1 = PairingAUtils.getRandomElementOfG1();
   Element e = PairingAUtils.doPairing(g,g1);
   ```


## 1.8 如何获取指定算法的密钥

## 使用KeyUtils类获取密钥

1. 随机生成密钥

```java
//指定对称加密算法，随机生成其对称密钥字节数组
byte[] key = KeyUtils.getSecretKey(SE.SM4);
//指定非对称加密算法，随机生成其公钥对
AEKeyPair key = KeyUtils.getAEKeyPair(AE.SM2);
//指定非对称加密算法，随机生成其的公钥对
SigKeyPair keyPair = KeyUtils.getSigKeyPair(SIG.NONEWITHSM2);
```

2. 由给定的随机数种子生成固定密钥：给定同一个seed就生成同一个密钥

```java
byte[] seed = "seed".getBytes();
//指定对称加密算法，随机生成其对称密钥字节数组
byte[] key = KeyUtils.getSecretKeyBySeed(SE.SM4,seed);
//指定非对称加密算法，随机生成其公钥对
AEKeyPair key = KeyUtils.getAEKeyPairBySeed(AE.SM2,seed);
//指定非对称加密算法，随机生成其的公钥对
SigKeyPair keyPair = KeyUtils.getSigKeyPairBySeed(SIG.NONEWITHSM2,seed);
```

