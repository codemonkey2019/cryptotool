# cryptool工具包使用手册

## 快速开始

### 1.1 对称加密算法的实现（SM4算法举例）

```java
//随机获取一个密钥字节数组
byte[] key = KeyUtils.getSecretKey(SE.SM4);
//从算法工厂中获取SM4加密的MyCipher实例，并指定工作模式，填充模式和密钥
MyCipher cipher = MyCipherFactory.getSECipher(SE.SM4, Pattern.CBC, Padding.PKCS5,key);
//准备一个数据
byte[] data = "hello word".getBytes;
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
   Pairing pairing = PairingAUtils.getPairing();
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

   