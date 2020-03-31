1. 整合Spring Security和JWT实现后台用户的登录和授权功能  
2. 改造Swagger-UI的配置使其可以自动记住登录令牌进行发送

#【Spring Security】
Spring Security是基于Spring AOP和Servlet过滤器的安全框架。它提供全面的安全性解决方案，同时在Web请求级和方法调用级处理身份确认和授权

* Spring Security核心功能
1. 认证(你是谁，用户/设备/系统)
2. 验证(你能干什么，即权限控制/授权，允许执行的操作)
3. 攻击防护(防止伪造身份)

#【JWT】
JWT是JSON WEB TOKEN的缩写，它是基于 RFC 7519 标准定义的一种可以安全传输的的JSON对象，由于使用了数字签名，所以是可信任和安全的。

* JWT的组成:  
JWT token的格式：header.payload.signature
1. header中用于存放签名的生成算法
    {"alg": "HS512"}
2. payload中用于存放用户名、token的生成时间和过期时间
    {"sub":"admin","created":1489079981393,"exp":1489684781}
3. signature为以header和payload生成的签名，一旦header和payload被篡改，验证将失败
    //secret为加密算法的密钥
    String signature = HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)

* JWT实例 - 一个JWT的字符串:
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE1NTY3NzkxMjUzMDksImV4cCI6MTU1NzM4MzkyNX0.d-iki0193X0bBOETf2UN3r3PotNIEAV7mzIxxeI5IxFyzzkOZxS0PGfF_SK6wxCv2K8S0cZjMkv6b5bCqc0VBw

* JWT实现认证和授权的原理  
 1.用户调用登录接口，登录成功后获取到JWT的token  
 2.之后用户每次调用接口都在http的header中添加一个叫Authorization的头，值为JWT的token  
 3.后台程序通过对Authorization头中信息的解码及数字签名校验来获取其中的用户信息，从而实现认证和授权  