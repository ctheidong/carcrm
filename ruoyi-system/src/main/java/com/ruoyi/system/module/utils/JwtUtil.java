package com.ruoyi.system.module.utils;

import com.ruoyi.common.exception.TokenTimeOutException;
import com.ruoyi.system.domain.SysUser;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;

/**
 * @author hlf
 * 2020/5/27 17:11
 * 文件说明：
 */
@Component
public class JwtUtil {

//    @Autowired
//    private RedisClient redisClient;

    /**
     * 用户登录成功后生成Jwt
     * 使用Hs256算法  私匙使用用户密码
     *
     * @param ttlMillis jwt过期时间
     * @param user 登录成功的companyRegistered对象
     * @return
     */
    public String createJWT(long ttlMillis, SysUser user) {
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = new HashMap<String, Object>();
//        claims.put("id", user.getUserId());
        claims.put("username", user.getUserName());
        claims.put("password", user.getPassword());

        //生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        String key = user.getPassword();

        //生成签发人
        String subject = user.getUserName();

        //下面就是在为payload添加各种标准声明和私有声明了
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(UUID.randomUUID().toString())
                //iat: jwt的签发时间
                .setIssuedAt(now)
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            //设置过期时间
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * Token的解密
     * @param token 加密后的token
     * @param user  登录的对象
     * @return
     */
    public Claims parseJWT(String token, SysUser user) {
        //签名秘钥，和生成的签名的秘钥一模一样
        String key = user.getPassword();

        //得到DefaultJwtParser
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(key)
                //设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * 校验token
     *
     * 完整的参考下：
     * 1.将传过来的token解析，解析出来用户Id，用户账号，用户密码。
     * 2.比较解析出来的用户密码是否跟传过来的密码一致
     * 3.如果不一致，提示：token错误
     * 4.否则，验证token是否失效
     * 5.如果失效，提示：token已失效
     * 6.否则，进行下一步业务操作。
     * @param token
     * @param user
     * @return
     */
    public Boolean isVerify(String token, SysUser user) throws JwtException {
        Claims claims = null;
        try {
            //签名秘钥，和生成的签名的秘钥一模一样
            String key = user.getPassword();

            //得到DefaultJwtParser
            claims =Jwts.parser()
                    //设置签名的秘钥
                    .setSigningKey(key)
                    //设置需要解析的jwt
                    .parseClaimsJws(token).getBody();

        }catch (ExpiredJwtException e){
//            String newToken = createJWT(CacheTime.TIME_OUT,user);
//            redisClient.set("token:", newToken, CacheTime.CACHE_EXP_FOREVER);
            throw new TokenTimeOutException("token过期,请重新登录! ");
        }
        return claims.get("password").equals(user.getPassword());
    }

}
