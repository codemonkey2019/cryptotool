# cryptotool
该项目封装了java加解密包的一些算法。包括哈希、签名、对称和非对称加密。
需要BC库作为服务提供商
## 一些关键接口
MyCipher：做加解密的接口。可以使用CipherFactory类来获得对称和非对称加解密的实现类对象。需要传入预定义的算法名、工作模式、填充模式的枚举值作为参数
MySignature:做签名的接口。可以使用SignatureFactory类获得实现类对象
MyDigest：做哈希的接口。可以使用DigestFactory类获得实现类对象
OperateKey：生成和转化密钥的工具类。

具体使用方法见测试包
